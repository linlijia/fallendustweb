package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.modules.generator.dao.DeviceStatusHistoryDao;
import com.modules.generator.entity.DeviceStatusHistoryEntity;
import com.modules.generator.service.DeviceStatusHistoryService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("deviceStatusHistoryService")
public class DeviceStatusHistoryServiceImpl extends ServiceImpl<DeviceStatusHistoryDao, DeviceStatusHistoryEntity> implements DeviceStatusHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DeviceStatusHistoryEntity> page = this.selectPage(
                new Query<DeviceStatusHistoryEntity>(params).getPage(),
                new EntityWrapper<DeviceStatusHistoryEntity>()
        );

        return new PageUtils(page);
    }

}
