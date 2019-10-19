package com.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.generator.entity.DeviceStatusExHistory;

import java.util.Map;

/**
 * @author linli
 */
public interface DeviceStatusHistoryExExService extends IService<DeviceStatusExHistory> {
    PageUtils queryPage(Map<String, Object> params);
}
