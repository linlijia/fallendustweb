package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.modules.generator.dao.DeviceStatusExExDao;
import com.modules.generator.entity.DeviceStatusEx;
import com.modules.generator.service.DeviceStatusExExService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author linli
 */
@Service("deviceStatusExExService")
public class DeviceStatusExExServiceImpl extends ServiceImpl<DeviceStatusExExDao, DeviceStatusEx> implements DeviceStatusExExService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<DeviceStatusEx> ew = new EntityWrapper();
        if ("like".equals(params.get("searchType")) && params.get("key") != null
                && !params.get("key").toString().trim().equals("")) {
            String key = params.get("key").toString();
            String value = params.get("value") == null ? "" : params.get("value").toString();
            ew.like(key, value);
        }
        if (params.get("siteId") != null) {
            ew.eq("site_id", params.get("siteId"));
        }

        Page<DeviceStatusEx> page = this.selectPage(
                new Query<DeviceStatusEx>(params).getPage(),
                ew
        );

        return new PageUtils(page);
    }
}
