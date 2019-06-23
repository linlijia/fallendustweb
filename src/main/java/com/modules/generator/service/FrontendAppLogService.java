package com.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.generator.entity.FrontendLogEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface FrontendAppLogService extends IService<FrontendLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
