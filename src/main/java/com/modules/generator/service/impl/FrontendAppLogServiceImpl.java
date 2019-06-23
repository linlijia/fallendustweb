package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.modules.generator.dao.FrontendAppLogDao;
import com.modules.generator.entity.FrontendLogEntity;
import com.modules.generator.service.FrontendAppLogService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("frontendAppLogService")
public class FrontendAppLogServiceImpl extends ServiceImpl<FrontendAppLogDao, FrontendLogEntity> implements FrontendAppLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        return null;
    }
}
