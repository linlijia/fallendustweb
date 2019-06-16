package com.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.generator.entity.DeviceStatusHistoryEntity;

import java.util.Map;

/**
 * @author novacaine
 * @email xxxxx@gmail.com
 * @date 2019-04-21 16:56:50
 */
public interface DeviceStatusHistoryService extends IService<DeviceStatusHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

