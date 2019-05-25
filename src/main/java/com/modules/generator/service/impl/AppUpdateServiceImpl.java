package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.modules.generator.dao.AppUpdateDao;
import com.modules.generator.entity.AppUpdateEntity;
import com.modules.generator.service.AppUpdateService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("appUpdateService")
public class AppUpdateServiceImpl extends ServiceImpl<AppUpdateDao, AppUpdateEntity> implements AppUpdateService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AppUpdateEntity> page = this.selectPage(
                new Query<AppUpdateEntity>(params).getPage(),
                new EntityWrapper<AppUpdateEntity>()
        );

        return new PageUtils(page);
    }

}
