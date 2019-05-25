package com.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.generator.entity.OpsImageEntity;

import java.util.Map;

public interface OpsImageService extends IService<OpsImageEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

