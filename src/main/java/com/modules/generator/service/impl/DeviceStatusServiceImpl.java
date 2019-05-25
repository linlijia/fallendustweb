package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.modules.generator.dao.DeviceStatusDao;
import com.modules.generator.service.DeviceStatusService;
import com.modules.generator.entity.DeviceStatusEntity;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("deviceStatusService")
public class DeviceStatusServiceImpl extends ServiceImpl<DeviceStatusDao, DeviceStatusEntity> implements DeviceStatusService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<DeviceStatusEntity> ew = new EntityWrapper();
        if ("like".equals(params.get("searchType")) && params.get("key") != null
                && params.get("key").toString().trim() != "") {
            String key = params.get("key").toString();
            String value = params.get("value") == null ? "" : params.get("value").toString();
            ew.like(key, value);
        }
        if (params.get("siteId") != null) {
            ew.eq("site_id", params.get("siteId"));
        }

        Page<DeviceStatusEntity> page = this.selectPage(
                new Query<DeviceStatusEntity>(params).getPage(),
                ew
        );

        return new PageUtils(page);
    }

}
