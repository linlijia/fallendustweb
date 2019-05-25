package com.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.generator.entity.AppUpdateEntity;

import java.util.Map;

/**
 * 程序更新
 *
 * @author novacaine
 * @email xxxxx@gmail.com
 * @date 2019-03-29 08:11:49
 */
public interface AppUpdateService extends IService<AppUpdateEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

