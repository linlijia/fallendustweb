package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.modules.generator.dao.FrontendAppStatusDao;
import com.modules.generator.entity.FrontendAppStatusEntity;
import com.modules.generator.service.FrontendAppStatusService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("frontendAppStatusService")
public class FrontendAppStatusServiceImpl extends ServiceImpl<FrontendAppStatusDao, FrontendAppStatusEntity> implements FrontendAppStatusService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        EntityWrapper<FrontendAppStatusEntity> ew = new EntityWrapper();
//        if ("like".equals(params.get("searchType")) && params.get("key") != null
//                && params.get("key").toString().trim() != "") {
//            String key = params.get("key").toString();
//            String value = params.get("value") == null ? "" : params.get("value").toString();
//            ew.like(key, value);
//        }
//        if (params.get("siteId") != null) {
//            ew.eq("site_id", params.get("siteId"));
//        }
//
//        Page<FrontendAppStatusEntity> page = this.selectPage(
//                new Query<FrontendAppStatusEntity>(params).getPage(),
//                ew
//        );

//        return new PageUtils(page);
        return null;
    }

}
