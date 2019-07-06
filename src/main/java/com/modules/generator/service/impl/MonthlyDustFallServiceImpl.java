package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.modules.generator.dao.MonthlyDustFallDao;
import com.modules.generator.entity.MonthlyDustFall;
import com.modules.generator.pojo.MonthlyDustFallVo;
import com.modules.generator.service.MonthlyDustFallService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : linli
 * @time : 2019/7/5 14:02
 * @description :
 */
@Service("monthlyDustFallService")
public class MonthlyDustFallServiceImpl extends ServiceImpl<MonthlyDustFallDao, MonthlyDustFall>
        implements MonthlyDustFallService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<MonthlyDustFall> ew = new EntityWrapper();
        if ("like".equals(params.get("searchType")) && params.get("key") != null
                && params.get("key").toString().trim() != "") {
            String key = params.get("key").toString();
            String value = params.get("value") == null ? "" : params.get("value").toString();
            ew.like(key, value);
        }
        Page<MonthlyDustFall> page = this.selectPage(
                new Query<MonthlyDustFall>(params).getPage(),
                ew
        );
        return new PageUtils(page);
    }

    @Override
    public List<MonthlyDustFall> selectByDataTime(int year, int month) {
        return this.selectByDataTime(year, month);
    }

    @Override
    public Map<String, MonthlyDustFallVo> selectLastestData() {
        Map<String, MonthlyDustFallVo> dataMap = new HashMap<>();
        List<MonthlyDustFallVo> dataList = this.baseMapper.selectLastestData();
        for (MonthlyDustFallVo monthlyDustFallVo : dataList) {
            dataMap.put(monthlyDustFallVo.getSiteName(), monthlyDustFallVo);
        }
        return dataMap;
    }


}
