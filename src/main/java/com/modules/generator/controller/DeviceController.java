package com.modules.generator.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.common.annotation.SysLog;
import com.common.utils.PageUtils;
import com.common.utils.R;
import com.common.utils.RedisUtils;
import com.modules.generator.Enums;
import com.modules.generator.entity.DeviceEntity;
import com.modules.generator.entity.SiteEntity;
import com.modules.generator.service.DeviceService;
import com.modules.generator.service.DeviceStatusService;
import com.modules.generator.service.SiteService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("generator/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private DeviceStatusService deviceStatusService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:device:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = deviceService.listDeviceSite(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:device:info")
    public R info(@PathVariable("id") Integer id) {
        DeviceEntity device = deviceService.selectById(id);

        return R.ok().put("device", device);
    }

    /**
     * 保存
     */
    @SysLog("新增设备")
    @RequestMapping("/save")
    @RequiresPermissions("generator:device:save")
    public R save(@RequestBody DeviceEntity device) {
        if (device.getSiteId() != null) {

            SiteEntity siteEntity = siteService.selectById(device.getSiteId());
            device.setSiteName(siteEntity.getSite());
            device.setCity(siteEntity.getCity());
        }
        deviceService.insert(device);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改设备")
    @RequestMapping("/update")
    @RequiresPermissions("generator:device:update")
    public R update(@RequestBody DeviceEntity device) {
        deviceService.updateById(device);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除设备")
    @RequestMapping("/delete")
    @RequiresPermissions("generator:device:delete")
    public R delete(@RequestBody Integer[] ids) {
        deviceService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 设备状态
     */
    @RequestMapping("/status")
    /**
     * @RequiresPermissions("generator:device:status")
     */
    public R status() {
        int total = deviceService.selectCount(null);
        int failure = deviceService.selectCount(new EntityWrapper<DeviceEntity>().eq("operation", 0));
        Calendar beforeTime = Calendar.getInstance();
        // 5分钟没上报状态数据就认为是离线s
        beforeTime.add(Calendar.MINUTE, -5);
        int onlineCount = deviceStatusService.onlineCount(beforeTime.getTime());

        Map<String, Integer> result = new HashMap<>();
        //总设备
        result.put("total", total);
        //设备在线
        result.put("online", onlineCount);
        //设备离线
        result.put("offline", total - onlineCount);
        //设备故障
        result.put("failure", failure);
        return R.ok().put("count", result);
    }

    /**
     * 控制设备
     */
    @RequestMapping("/command/execute")
    public R command(@RequestBody Map<String, Object> cmd) {
        List<String> mns = (List<String>) cmd.get("mns");
        String commandCode = (String) cmd.get("code");
        for (String mn : mns) {
            Map<String, Object> ops = new HashMap<>(1);
            ops.put(mn, commandCode);
            Long opsId = redisUtils.leftPush("huanbao", ops);
        }
        return R.ok();
    }

    /**
     * 设备状态
     */
    @RequestMapping("/command/list")
    public R commandList() {
        List<Map<String, String>> cmds = new ArrayList<>(28);
        for (Enums.DeviceCommand deviceCommand : Enums.DeviceCommand.values()) {
            Map cmd = new HashMap<>();
            cmd.put("value", deviceCommand.getCode());
            cmd.put("label", deviceCommand.getCmdName());
            cmds.add(cmd);
        }
        return R.ok().put("data", cmds);
    }

    /**
     * 根据MN查询
     */
    @RequestMapping("/mn/{mn}")
    public R infoByMn(@PathVariable("mn") String mn) {
        return R.ok().put("device", deviceService.selectByMn(mn));
    }

}
