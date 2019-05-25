package com.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.generator.entity.NotifyEntity;

import java.util.Map;

public interface NotifyService extends IService<NotifyEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

