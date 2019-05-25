package com.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.generator.entity.OpsRecordEntity;

import java.util.Map;

public interface OpsRecordService extends IService<OpsRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

