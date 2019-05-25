package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.modules.generator.service.OpsTaskService;
import com.modules.generator.dao.OpsTaskDao;
import com.modules.generator.entity.OpsTaskEntity;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("opsTaskService")
public class OpsTaskServiceImpl extends ServiceImpl<OpsTaskDao, OpsTaskEntity> implements OpsTaskService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<OpsTaskEntity> ew = new EntityWrapper();
        if ("like".equals(params.get("searchType")) && params.get("key") != null
                && params.get("key").toString().trim() != "") {
            String key = params.get("key").toString();
            String value = params.get("value") == null ? "" : params.get("value").toString();
            ew.like(key, value);
        }
        Page<OpsTaskEntity> page = this.selectPage(
                new Query<OpsTaskEntity>(params).getPage(),
                ew
        );

        return new PageUtils(page);
    }

}
