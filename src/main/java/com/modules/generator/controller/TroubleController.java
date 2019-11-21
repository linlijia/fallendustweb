package com.modules.generator.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.common.utils.DateUtils;
import com.common.utils.PageUtils;
import com.common.utils.R;
import com.common.utils.ShiroUtils;
import com.modules.generator.Enums;
import com.modules.generator.entity.DeviceEntity;
import com.modules.generator.entity.NotifyEntity;
import com.modules.generator.entity.TroubleEntity;
import com.modules.generator.service.DeviceService;
import com.modules.generator.service.NotifyService;
import com.modules.generator.service.TroubleService;
import com.modules.sys.entity.SysUserEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * @author novacaine
 * @email superman@gmail.com
 * @date 2019-06-23 23:54:50
 */
@RestController
@RequestMapping("generator/trouble")
public class TroubleController {
    @Autowired
    private TroubleService troubleService;
    @Autowired
    private NotifyService notifyService;
    @Autowired
    private DeviceService deviceService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:trouble:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = troubleService.queryTroubleVOPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:trouble:info")
    public R info(@PathVariable("id") Integer id) {
        TroubleEntity trouble = troubleService.selectById(id);

        return R.ok().put("trouble", trouble);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TroubleEntity trouble) {
        troubleService.insert(trouble);
        SysUserEntity userEntity = ShiroUtils.getUserEntity();
        NotifyEntity notifyEntity = new NotifyEntity();
        notifyEntity.setCreator(userEntity.getUserId().intValue());
        notifyEntity.setCreatorName(userEntity.getName());
        notifyEntity.setUserName("前端系统");
        notifyEntity.setType(Enums.NotifyMsgType.Trouble.getMsgType());
        List<DeviceEntity> deviceEntities = deviceService.selectByMn(trouble.getMn());
        if (deviceEntities != null && deviceEntities.size() > 0) {
            notifyEntity.setContent("站点：" + deviceEntities.get(0).getSiteName() + "，设备发生故障，" +
                    "设备mn：" + trouble.getMn() + " 故障编码：" + trouble.getTroubleCode() + " 故障类型：" + trouble.getTroublCodeName()
                    + "故障描述：" + trouble.getTroubleDescription() + " 发生时间："
                    + DateUtils.format(trouble.getHappenTime(),DateUtils.DATE_TIME_PATTERN)
            );
        }
        notifyService.insert(notifyEntity);
        return R.ok();
    }

    /**
     * 批量保存
     */
    @RequestMapping("/savelist")
    public R saveList(@RequestBody List<TroubleEntity> troubleList) {
        troubleService.insertOrUpdateBatch(troubleList);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:trouble:update")
    public R update(@RequestBody TroubleEntity trouble) {
        troubleService.updateById(trouble);

        return R.ok();
    }

    /**
     * 修改前端
     */
    @PutMapping("/put")
    public R put(@RequestBody TroubleEntity trouble) {
        EntityWrapper<TroubleEntity> wrapper = new EntityWrapper<>();
        wrapper.where("happen_time = '" + trouble.getHappenTime() + "' AND mn = '" + trouble.getMn() + "'");
        TroubleEntity selectedTrouble = (TroubleEntity) troubleService.selectObj(wrapper);
        if (selectedTrouble != null) {
            selectedTrouble.setSolved(trouble.getSolved());
            selectedTrouble.setSolvedMethod(trouble.getSolvedMethod());
            selectedTrouble.setSolvedTime(trouble.getSolvedTime());
            selectedTrouble.setTroubleShooter(trouble.getTroubleShooter());
        }
        troubleService.insertOrUpdate(selectedTrouble);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:trouble:delete")
    public R delete(@RequestBody Integer[] ids) {
        troubleService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 异常状态列表
     */
    @RequestMapping("/type")
    public R type() {
        List<Map<String, String>> types = new ArrayList<>(28);
        for (Enums.TroubleType troubleType : Enums.TroubleType.values()) {
            Map type = new HashMap<>();
            type.put("value", troubleType.getCode());
            type.put("label", troubleType.getTroubleName());
            types.add(type);
        }
        return R.ok().put("data", types);
    }


    @RequestMapping("/ranking")
    public R Rinking(String month, String city) {
        /*
        1.按数据排序（降序）；
        2.离线天数，故障天数，异常天数；
        3.提供城市、年月选择；
        **/
        if (month == null || "".equals(month.trim())) {
            return R.error(400, "必须选择年月");
        }
        if (city == null || "".equals(city.trim())) {
            city = "上海市";
        }
        Date start = DateUtils.stringToDate(month, DateUtils.MONTH_PATTERN);
        Date end = DateUtils.addDateMonths(start, 1);
        return R.ok().put("data", troubleService.troubleRanking(start, end));
    }
}
