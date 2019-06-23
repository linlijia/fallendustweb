package com.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.generator.entity.DeviceStatusEntity;

import java.util.Date;
import java.util.Map;

public interface DeviceStatusService extends IService<DeviceStatusEntity> {

    PageUtils queryPage(Map<String, Object> params);

    int onlineCount(Date lastUploadDate);
}

