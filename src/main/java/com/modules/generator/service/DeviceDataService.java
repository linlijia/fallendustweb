package com.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.generator.entity.DeviceDataEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DeviceDataService extends IService<DeviceDataEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Map<String, Object>> queryHistoryData(Date startTime, Date endTime, String mn);

    /**
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param city      城市，默认上海
     * @return
     */
    Map<String, Object> calculation(Date startTime, Date endTime, String city);

    DeviceDataEntity selectLastMonthData(String mn, Date startTime, Date endTime);
}

