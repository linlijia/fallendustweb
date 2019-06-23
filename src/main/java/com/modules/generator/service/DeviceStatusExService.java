package com.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.generator.entity.DeviceStatus;

import java.util.Map;

public interface DeviceStatusExService extends IService<DeviceStatus> {

    PageUtils queryPage(Map<String, Object> params);
}
