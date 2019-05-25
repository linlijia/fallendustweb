package com.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.generator.entity.DeviceImageEntity;

import java.util.Map;

public interface DeviceImageService extends IService<DeviceImageEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

