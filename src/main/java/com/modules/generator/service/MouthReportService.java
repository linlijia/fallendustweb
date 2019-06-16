package com.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.generator.entity.MouthReportEntity;

import java.util.Map;

/**
 * @author novacaine
 * @email superman@gmail.com
 * @date 2019-06-16 19:24:39
 */
public interface MouthReportService extends IService<MouthReportEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

