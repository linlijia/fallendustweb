package com.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.generator.entity.MonthlyDustFall;
import com.modules.generator.pojo.MonthlyDustFallVo;

import java.util.List;
import java.util.Map;

/**
 * @author : linli
 * @time : 2019/7/5 14:01
 * @description :
 */
public interface MonthlyDustFallService extends IService<MonthlyDustFall> {
    PageUtils queryPage(Map<String, Object> params);

    List<MonthlyDustFall> selectByDataTime(int year, int month);

    Map<String, MonthlyDustFallVo> selectLastestData();
}
