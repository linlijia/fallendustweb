package com.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.generator.entity.DeviceStatusHistory;

import java.util.Map;

public interface DeviceStatusHistoryExService extends IService<DeviceStatusHistory> {
    PageUtils queryPage(Map<String, Object> params);
}
