package com.modules.generator.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.common.utils.DateUtils;
import com.common.utils.PageUtils;
import com.common.utils.R;
import com.modules.generator.entity.DeviceDataEntity;
import com.modules.generator.entity.DeviceEntity;
import com.modules.generator.service.DeviceDataService;
import com.modules.generator.service.DeviceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("generator/devicedata")
public class DeviceDataController {
    @Autowired
    private DeviceDataService deviceDataService;
    @Autowired
    private DeviceService deviceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:devicedata:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = deviceDataService.queryPage(params);
        List<DeviceDataEntity> deviceDataEntities = (List<DeviceDataEntity>) page.getList();
        if (deviceDataEntities != null) {
            for (DeviceDataEntity deviceDataEntity : deviceDataEntities) {
                if (deviceDataEntity.getA34011Rtd() == null || deviceDataEntity.getA34011Day() == null) {
                    continue;
                }
                deviceDataEntity.setDustData(String.format("%.2f",
                        deviceDataEntity.getA34011Rtd() / 353.25 * 10000 * 30 / deviceDataEntity.getA34011Day()));
            }
        }
        page.setList(deviceDataEntities);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:devicedata:info")
    public R info(@PathVariable("id") Integer id) {
        DeviceDataEntity deviceData = deviceDataService.selectById(id);

        return R.ok().put("deviceData", deviceData);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:devicedata:save")
    public R save(@RequestBody DeviceDataEntity deviceData) {
        if (deviceData.getA34011Day() == null) {
            return R.error(400, "a34011Day参数不能为空，a34011Day,a34011Rtd,a34011Flag是必填参数");
        }
        if (deviceData.getA34011Rtd() == null) {
            return R.error(400, "a34011Rtd参数不能为空，a34011Day,a34011Rtd,a34011Flag是必填参数");
        }
        if (deviceData.getA34011Ori() == null) {
            return R.error(400, "a34011Ori参数不能为空，a34011Day,a34011Rtd,a34011Flag是必填参数");
        }
        if (deviceData.getA34011Flag() == null) {
            return R.error(400, "a34011Flag参数不能为空，a34011Day,a34011Rtd,a34011Flag是必填参数");
        }
        DeviceEntity deviceEntity = deviceService.selectOne(new EntityWrapper<DeviceEntity>().eq("mn", deviceData.getMn()));
        deviceData.setSiteId(deviceEntity.getSiteId());
        deviceData.setSiteName(deviceEntity.getSiteName());
        deviceData.setCity(deviceEntity.getCity());
        deviceDataService.insert(deviceData);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:devicedata:update")
    public R update(@RequestBody DeviceDataEntity deviceData) {
        deviceDataService.updateById(deviceData);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:devicedata:delete")
    public R delete(@RequestBody Integer[] ids) {
        deviceDataService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/drawList")
    /**
     *   @RequiresPermissions("generator:devicedata:drawList")
     */
    public R drawList(@RequestParam Map<String, Object> params) {
        EntityWrapper<DeviceDataEntity> entityEntityWrapper = new EntityWrapper<>();
        Date startTime = null;
        Date endTime = null;
        String mn = null;
        if (params.get("startTime") != null) {
            startTime = new Date(Long.parseLong(params.get("startTime").toString()));

        } else {
            startTime = DateUtils.addDateDays(new Date(), -30);
        }
        if (params.get("endTime") != null) {
            endTime = new Date(Long.parseLong(params.get("endTime").toString()));

        } else {
            endTime = new Date();
        }

        if (params.get("mn") != null) {
            mn = params.get("mn").toString();
        }

        return R.ok().put("data", deviceDataService.queryHistoryData(startTime, endTime, mn));
    }
}
