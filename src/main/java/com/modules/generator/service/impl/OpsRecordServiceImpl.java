package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.modules.generator.entity.OpsRecordEntity;
import com.modules.generator.service.OpsRecordService;
import com.modules.generator.dao.OpsRecordDao;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("opsRecordService")
public class OpsRecordServiceImpl extends ServiceImpl<OpsRecordDao, OpsRecordEntity> implements OpsRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<OpsRecordEntity> ew = new EntityWrapper();
        if ("like".equals(params.get("searchType")) && params.get("key") != null
                && params.get("key").toString().trim() != "") {
            String key = params.get("key").toString();
            String value = params.get("value") == null ? "" : params.get("value").toString();
            ew.like(key, value);
        }
        Page<OpsRecordEntity> page = this.selectPage(
                new Query<OpsRecordEntity>(params).getPage(),
                ew
        );

        return new PageUtils(page);
    }

}
