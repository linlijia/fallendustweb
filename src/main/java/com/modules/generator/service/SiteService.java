package com.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.generator.entity.SiteEntity;

import java.util.Map;

public interface SiteService extends IService<SiteEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

