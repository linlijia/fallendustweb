package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.modules.generator.dao.DeviceStatusExDao;
import com.modules.generator.entity.DeviceStatus;
import com.modules.generator.service.DeviceStatusExService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("deviceStatusExService")
public class DeviceStatusExServiceImpl extends ServiceImpl<DeviceStatusExDao, DeviceStatus> implements DeviceStatusExService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<DeviceStatus> ew = new EntityWrapper();
        if ("like".equals(params.get("searchType")) && params.get("key") != null
                && !params.get("key").toString().trim().equals("")) {
            String key = params.get("key").toString();
            String value = params.get("value") == null ? "" : params.get("value").toString();
            ew.like(key, value);
        }
        if (params.get("siteId") != null) {
            ew.eq("site_id", params.get("siteId"));
        }

        Page<DeviceStatus> page = this.selectPage(
                new Query<DeviceStatus>(params).getPage(),
                ew
        );

        return new PageUtils(page);
    }
}
