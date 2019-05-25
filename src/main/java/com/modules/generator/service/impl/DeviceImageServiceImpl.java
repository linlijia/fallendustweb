package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.modules.generator.dao.DeviceImageDao;
import com.modules.generator.service.DeviceImageService;
import com.modules.generator.entity.DeviceImageEntity;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("deviceImageService")
public class DeviceImageServiceImpl extends ServiceImpl<DeviceImageDao, DeviceImageEntity> implements DeviceImageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        EntityWrapper<DeviceImageEntity> ew = new EntityWrapper();
        if ("like".equals(params.get("searchType")) && params.get("key") != null
                && params.get("key").toString().trim() != "") {
            String key = params.get("key").toString();
            String value = params.get("value") == null ? "" : params.get("value").toString();
            ew.like(key, value);
        } else {
            if (params.get("mn") != null) {
                ew.eq("mn", params.get("mn"));
            } else if (params.get("id") != null) {
                ew.eq("id", params.get("id"));
            }
        }
        Page<DeviceImageEntity> page = this.selectPage(
                new Query<DeviceImageEntity>(params).getPage(),
                ew
        );

        return new PageUtils(page);
    }

}
