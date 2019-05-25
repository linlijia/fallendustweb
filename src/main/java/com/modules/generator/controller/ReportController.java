package com.modules.generator.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.common.utils.DateUtils;
import com.common.utils.R;
import com.modules.generator.entity.DeviceDataEntity;
import com.modules.generator.entity.SiteEntity;
import com.modules.generator.service.DeviceDataService;
import com.modules.generator.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("generator/devicedata")
public class ReportController {
    @Autowired
    private DeviceDataService deviceDataService;
    @Autowired
    private SiteService siteService;

    @RequestMapping("/monthly")
    public R MonthlyDust(String month, String city) {
        /*
        1.按数据排序（降序）；
        2.默认显示当前月预测的月均数据；
        3.提供城市、年月选择；
        4. 计算将降尘取最后一条数据计算
        **/
        EntityWrapper<DeviceDataEntity> deviceDataEntityWrapper = new EntityWrapper<>();
        if (month == null || "".equals(month.trim())) {
            return R.error(400, "必须选择年月");
        }
        if (city == null || "".equals(city.trim())) {
            city = "上海市";
        }
        deviceDataEntityWrapper.eq("city", city);
        Date start = DateUtils.stringToDate(month, DateUtils.MONTH_PATTERN);
        Date end = DateUtils.addDateMonths(start, 1);
        deviceDataEntityWrapper.gt("data_time", start);
        deviceDataEntityWrapper.lt("data_time", end);
        Map<String, Object> deviceDataEntities = deviceDataService.calculation(start, end, city);
        Map<String, Map<String, Object>> dustData = (Map<String, Map<String, Object>>) deviceDataEntities.get("dustData");
        List<SiteEntity> siteEntities = siteService.selectList(new EntityWrapper<SiteEntity>().eq("city", city));
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, Map<String, Object>> entry : dustData.entrySet()) {
            result.put(entry.getKey(), entry.getValue().get("avgDust"));
        }
        for (SiteEntity siteEntity : siteEntities) {
            if (result.get(siteEntity.getSite()) == null) {
                result.put(siteEntity.getSite(), "NaN");
            }
        }
        deviceDataEntities.put("dustData", result);
        deviceDataEntities.put("dustDataAvg", deviceDataEntities.get("dustDataAvg"));

        return R.ok().put("data", deviceDataEntities);
    }
}
