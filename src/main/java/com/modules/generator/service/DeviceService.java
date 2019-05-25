package com.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.generator.entity.DeviceEntity;
import com.modules.generator.pojo.DeviceSiteVO;

import java.util.List;
import java.util.Map;

public interface DeviceService extends IService<DeviceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<DeviceEntity> selectByMn(String mn);

    List<DeviceEntity> selectBySiteId(Integer siteId);

    PageUtils listDeviceSite(Map<String, Object> param);

    DeviceSiteVO selectByDeviceMn(String mn);
}

