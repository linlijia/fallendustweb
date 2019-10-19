package com.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.generator.entity.DeviceStatus;
import com.modules.generator.entity.DeviceStatusEx;

import java.util.Map;

/**
 * @author linli
 */
public interface DeviceStatusExExService extends IService<DeviceStatusEx> {

    PageUtils queryPage(Map<String, Object> params);
}
