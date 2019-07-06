package com.modules.generator.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.common.utils.PageUtils;
import com.common.utils.R;
import com.modules.generator.Enums;
import com.modules.generator.entity.DeviceEntity;
import com.modules.generator.entity.DeviceStatus;
import com.modules.generator.entity.DeviceStatusEntity;
import com.modules.generator.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("generator/devicestatus")
public class DeviceStatusController {
    @Autowired
    private DeviceStatusService deviceStatusService;
    @Autowired
    private DeviceStatusExService deviceStatusExService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceStatusHistoryService deviceStatusHistoryService;
    @Autowired
    private DeviceStatusHistoryExService deviceStatusHistoryExService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:devicestatus:list")
    public R list(@RequestParam Map<String, Object> params) {
        //增加参数 last=true表示只查最后一条记录
        PageUtils page = deviceStatusExService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:devicestatus:info")
    public R info(@PathVariable("id") Integer id) {
        DeviceStatusEntity deviceStatus = deviceStatusService.selectById(id);

        return R.ok().put("deviceStatus", deviceStatus);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:devicestatus:save")
    public R save(@RequestBody DeviceStatusEntity deviceStatus) {
        DeviceEntity deviceEntity = deviceService.selectOne(new EntityWrapper<DeviceEntity>().eq("mn", deviceStatus.getMn()));
        if (deviceEntity != null) {
            deviceEntity.setOperation(deviceStatus.getOperation());
            deviceService.insertOrUpdate(deviceEntity);
            deviceStatus.setSiteId(deviceEntity.getSiteId());
            deviceStatus.setSiteName(deviceEntity.getSiteName());
            deviceStatus.setCity(deviceEntity.getCity());
        }

        //插入历史数据表
        deviceStatusHistoryService.insert(deviceStatus.convert2DeviceStatusHistory());

        //插入当前数据表
        List<DeviceStatusEntity> deviceStatusEntity = deviceStatusService.selectList(
                new EntityWrapper<DeviceStatusEntity>().eq("mn", deviceStatus.getMn()));
        if (deviceStatusEntity != null && !deviceStatusEntity.isEmpty()) {
            deviceStatus.setId(deviceStatusEntity.get(0).getId());
        }
        deviceStatusService.insertOrUpdate(deviceStatus);
        return R.ok();
    }

    /**
     * 保存
     */
    @RequestMapping("/save/ex")
    @RequiresPermissions("generator:devicestatus:save")
    public R saveEx(@RequestBody DeviceStatus deviceStatus) {
        DeviceEntity deviceEntity = deviceService.selectOne(new EntityWrapper<DeviceEntity>().eq("mn", deviceStatus.getMn()));
        DeviceStatusEntity deviceStatusEntity = deviceStatus.convert2DeviceStatusEntity();
        if (deviceEntity != null) {
            if (deviceStatus.getOperation() == Enums.TroubleType.NO_TROUBLE.getCode()) {
                deviceEntity.setOperation(0);
            } else {
                deviceEntity.setOperation(1);
            }
            deviceService.insertOrUpdate(deviceEntity);
            deviceStatus.setSiteId(deviceEntity.getSiteId());
            deviceStatusEntity.setSiteId(deviceEntity.getSiteId());
            deviceStatus.setSiteName(deviceEntity.getSiteName());
            deviceStatusEntity.setSiteName(deviceEntity.getSiteName());
            deviceStatus.setCity(deviceEntity.getCity());
            deviceStatusEntity.setCity(deviceEntity.getCity());
        }
        //插入当前数据表 现用
        List<DeviceStatusEntity> deviceStatusEntityList = deviceStatusService.selectList(
                new EntityWrapper<DeviceStatusEntity>().eq("mn", deviceStatus.getMn()));
        if (deviceStatusEntityList != null && !deviceStatusEntityList.isEmpty()) {
            deviceStatusEntity.setId(deviceStatusEntityList.get(0).getId());
        }
        deviceStatusService.insertOrUpdate(deviceStatusEntity);
        //插入历史数据表
        deviceStatusHistoryExService.insert(deviceStatus.convert2DeviceStatusHistory());

        //插入当前数据表
        List<DeviceStatus> deviceStatusList = deviceStatusExService.selectList(
                new EntityWrapper<DeviceStatus>().eq("mn", deviceStatus.getMn()));
        if (deviceStatusList != null && !deviceStatusList.isEmpty()) {
            deviceStatus.setId(deviceStatusList.get(0).getId());
        }
        deviceStatusExService.insertOrUpdate(deviceStatus);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:devicestatus:update")
    public R update(@RequestBody DeviceStatusEntity deviceStatus) {
        if (deviceStatusService.updateById(deviceStatus)) {
            DeviceEntity deviceEntity = deviceService.selectOne(new EntityWrapper<DeviceEntity>().eq("mn", deviceStatus.getMn()));
            if (deviceEntity != null) {
                deviceEntity.setOperation(deviceStatus.getOperation());
                deviceService.insertOrUpdate(deviceEntity);
            }
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:devicestatus:delete")
    public R delete(@RequestBody Integer[] ids) {
        deviceStatusService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 设备离线
     */
    @RequestMapping("/offline/{mn}")
    public R offline(@PathVariable("mn") String mn) {
        DeviceStatusEntity deviceStatusEntity = new DeviceStatusEntity();
        DeviceEntity deviceEntity = deviceService.selectOne(new EntityWrapper<DeviceEntity>().eq("mn", mn));
        if (deviceEntity != null) {
            deviceStatusEntity.setSiteId(deviceEntity.getSiteId());
            deviceStatusEntity.setSiteName(deviceEntity.getSiteName());
            deviceStatusEntity.setCity(deviceEntity.getCity());
        } else {
            return R.error(400, "设备不存在");

        }


        //插入当前数据表
        List<DeviceStatusEntity> deviceStatusEntityList = deviceStatusService.selectList(
                new EntityWrapper<DeviceStatusEntity>().eq("mn", mn));
        if (deviceStatusEntityList != null && !deviceStatusEntityList.isEmpty()) {
            deviceStatusEntity.setId(deviceStatusEntityList.get(0).getId());
        }
        deviceStatusEntity.setMn(mn);
        deviceStatusEntity.setStatus(false);
        deviceStatusService.insertOrUpdate(deviceStatusEntity);
        //插入历史数据表

        deviceStatusHistoryService.insert(deviceStatusEntity.convert2DeviceStatusHistory());

        return R.ok();
    }


}
