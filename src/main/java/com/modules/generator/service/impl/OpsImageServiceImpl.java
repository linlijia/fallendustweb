package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.modules.generator.service.OpsImageService;
import com.modules.generator.dao.OpsImageDao;
import com.modules.generator.entity.OpsImageEntity;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("opsImageService")
public class OpsImageServiceImpl extends ServiceImpl<OpsImageDao, OpsImageEntity> implements OpsImageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<OpsImageEntity> ew = new EntityWrapper();
        if ("like".equals(params.get("searchType")) && params.get("key") != null
                && params.get("key").toString().trim() != "") {
            String key = params.get("key").toString();
            String value = params.get("value") == null ? "" : params.get("value").toString();
            ew.like(key, value);
        }
        Page<OpsImageEntity> page = this.selectPage(
                new Query<OpsImageEntity>(params).getPage(),
                ew
        );

        return new PageUtils(page);
    }

}
