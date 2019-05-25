package com.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.generator.entity.OpsTaskEntity;

import java.util.Map;

public interface OpsTaskService extends IService<OpsTaskEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

