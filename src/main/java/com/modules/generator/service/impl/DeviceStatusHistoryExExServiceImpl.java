package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;
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
        params.put("sidx", "dataTime");
        params.put("order", "ASC");
        EntityWrapper<DeviceStatusExHistory> wrapper = new EntityWrapper();
        wrapper.where("mn = \"" + params.get("mn") + "\" AND data_time BETWEEN \""
                +params.get("taskStartTime")+"\" AND \"" + params.get("taskEndTime") + "\"");
        int systemMode = Integer.parseInt((String)params.get("systemMode"));
        if (systemMode != 0 ) {
            wrapper.and("system_mode = " + systemMode);
        }
        Page<DeviceStatusExHistory> page = this.selectPage(
                new Query<DeviceStatusExHistory>(params).getPage(),
                wrapper
        );
        return new PageUtils(page);
    }
}
