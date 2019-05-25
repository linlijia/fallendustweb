package com.modules.generator.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.modules.generator.entity.DeviceStatusHistoryEntity;
import com.modules.generator.service.DeviceStatusHistoryService;
import com.common.utils.PageUtils;
import com.common.utils.R;


/**
 * @author novacaine
 * @email xxxxx@gmail.com
 * @date 2019-04-21 16:56:50
 */
@RestController
@RequestMapping("generator/devicestatushistory")
public class DeviceStatusHistoryController {
    @Autowired
    private DeviceStatusHistoryService deviceStatusHistoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:devicestatushistory:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = deviceStatusHistoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:devicestatushistory:info")
    public R info(@PathVariable("id") Integer id) {
        DeviceStatusHistoryEntity deviceStatusHistory = deviceStatusHistoryService.selectById(id);

        return R.ok().put("deviceStatusHistory", deviceStatusHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:devicestatushistory:save")
    public R save(@RequestBody DeviceStatusHistoryEntity deviceStatusHistory) {
        deviceStatusHistoryService.insert(deviceStatusHistory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:devicestatushistory:update")
    public R update(@RequestBody DeviceStatusHistoryEntity deviceStatusHistory) {
        deviceStatusHistoryService.updateById(deviceStatusHistory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:devicestatushistory:delete")
    public R delete(@RequestBody Integer[] ids) {
        deviceStatusHistoryService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
