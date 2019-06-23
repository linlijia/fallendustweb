package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.modules.generator.dao.DeviceStatusHistoryExDao;
import com.modules.generator.entity.DeviceStatusHistory;
import com.modules.generator.service.DeviceStatusHistoryExService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("deviceStatusHistoryExService")
public class DeviceStatusHistoryExServiceImpl extends ServiceImpl<DeviceStatusHistoryExDao, DeviceStatusHistory> implements DeviceStatusHistoryExService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        return null;
    }
}
