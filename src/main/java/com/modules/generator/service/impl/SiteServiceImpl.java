package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.modules.generator.dao.SiteDao;
import com.modules.generator.entity.SiteEntity;
import com.modules.generator.service.SiteService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("siteService")
public class SiteServiceImpl extends ServiceImpl<SiteDao, SiteEntity> implements SiteService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<SiteEntity> ew = new EntityWrapper();
        if ("like".equals(params.get("searchType")) && params.get("key") != null
                && params.get("key").toString().trim() != "") {
            String key = params.get("key").toString();
            String value = params.get("value") == null ? "" : params.get("value").toString();
            ew.like(key, value);
        }
        Page<SiteEntity> page = this.selectPage(
                new Query<SiteEntity>(params).getPage(),
                ew
        );

        return new PageUtils(page);
    }

}
