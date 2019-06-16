package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.modules.generator.dao.NotifyDao;
import com.modules.generator.entity.NotifyEntity;
import com.modules.generator.service.NotifyService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("notifyService")
public class NotifyServiceImpl extends ServiceImpl<NotifyDao, NotifyEntity> implements NotifyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<NotifyEntity> ew = new EntityWrapper();
        if ("like".equals(params.get("searchType")) && params.get("key") != null
                && params.get("key").toString().trim() != "") {
            String key = params.get("key").toString();
            String value = params.get("value") == null ? "" : params.get("value").toString();
            ew.like(key, value);
        }
        if (params.get("type") != null) {
            ew.eq("type", params.get("type"));
        }
        if (params.get("user_id") != null) {
            ew.eq("user_id", params.get("user_id"));
        }

        if (params.get("is_read") != null) {
            ew.eq("is_read", params.get("is_read"));
        } else {
            ew.eq("is_read", 0);
        }


        Page<NotifyEntity> page = this.selectPage(
                new Query<NotifyEntity>(params).getPage(),
                ew
        );

        return new PageUtils(page);
    }

}
