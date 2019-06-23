package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.modules.generator.dao.DeviceStatusExDao;
import com.modules.generator.entity.DeviceStatus;
import com.modules.generator.service.DeviceStatusExService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("deviceStatusExService")
public class DeviceStatusExServiceImpl extends ServiceImpl<DeviceStatusExDao, DeviceStatus> implements DeviceStatusExService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        return null;
    }
}
