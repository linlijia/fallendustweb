package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.modules.generator.dao.DeviceStatusHistoryExDao;
import com.modules.generator.dao.DeviceStatusHistoryExExDao;
import com.modules.generator.entity.DeviceStatusExHistory;
import com.modules.generator.entity.DeviceStatusHistory;
import com.modules.generator.service.DeviceStatusHistoryExExService;
import com.modules.generator.service.DeviceStatusHistoryExService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author linli
 */
@Service("deviceStatusHistoryExExService")
public class DeviceStatusHistoryExExServiceImpl extends ServiceImpl<DeviceStatusHistoryExExDao, DeviceStatusExHistory> implements DeviceStatusHistoryExExService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        return null;
    }
}
