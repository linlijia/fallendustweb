package com.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.generator.entity.FrontendAppStatusEntity;

import java.util.Map;

public interface FrontendAppStatusService extends IService<FrontendAppStatusEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
