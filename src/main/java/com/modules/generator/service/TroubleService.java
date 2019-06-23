package com.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.generator.entity.TroubleEntity;

import java.util.Map;

public interface TroubleService extends IService<TroubleEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
