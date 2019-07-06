package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.DateUtils;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.common.utils.R;
import com.modules.generator.dao.DeviceDataDao;
import com.modules.generator.entity.DeviceDataEntity;
import com.modules.generator.entity.SiteEntity;
import com.modules.generator.service.DeviceDataService;
import com.modules.generator.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("deviceDataService")
public class DeviceDataServiceImpl extends ServiceImpl<DeviceDataDao, DeviceDataEntity> implements DeviceDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<DeviceDataEntity> ew = new EntityWrapper();
        if ("like".equals(params.get("searchType")) && params.get("key") != null
                && params.get("key").toString().trim() != "") {
            String key = params.get("key").toString();
            String value = params.get("value") == null ? "" : params.get("value").toString();
            ew.like(key, value);
        }
        Page<DeviceDataEntity> page = this.selectPage(
                new Query<DeviceDataEntity>(params).getPage(),
                ew
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> queryHistoryData(Date startTime, Date endTime, String mn) {
        EntityWrapper<DeviceDataEntity> entityEntityWrapper = new EntityWrapper<>();
        entityEntityWrapper.lt("data_time", endTime);
        entityEntityWrapper.eq("mn", mn);
        entityEntityWrapper.gt("data_time", startTime);


        List<DeviceDataEntity> list = this.selectList(entityEntityWrapper);

        List<Map<String, Object>> result = new ArrayList();
        for (DeviceDataEntity deviceDataEntity : list) {
            Map<String, Object> m = new HashMap<>();
            m.put("dataTime", deviceDataEntity.getDataTime());
            m.put("a34011Rtd", deviceDataEntity.getA34011Rtd());
            m.put("a34011Ori", deviceDataEntity.getA34011Ori());
            result.add(m);
        }
        return result;
    }

    @Override
    public Map<String, Object> calculation(Date startTime, Date endTime, String city) {
        EntityWrapper<DeviceDataEntity> deviceDataEntityWrapper = new EntityWrapper<>();
        deviceDataEntityWrapper.eq("city", city);
        deviceDataEntityWrapper.gt("data_time", startTime);
        deviceDataEntityWrapper.lt("data_time", endTime);
        List<DeviceDataEntity> deviceDataEntities = this.selectList(deviceDataEntityWrapper);
        //找到每个站点，每个设备的最后一条数据
        Map<String, Map<String, DeviceDataEntity>> siteLastDivecesData = new HashMap<>();

        for (DeviceDataEntity deviceDataEntity : deviceDataEntities) {
            if (siteLastDivecesData.get(deviceDataEntity.getSiteName()) == null) {
                Map<String, DeviceDataEntity> siteAndRtd = new HashMap<>();
                siteLastDivecesData.put(deviceDataEntity.getSiteName(), siteAndRtd);
            }
            siteLastDivecesData.get(deviceDataEntity.getSiteName()).put(deviceDataEntity.getMn(), deviceDataEntity);
        }

        Map<String, Map<String, Object>> dustResult = new HashMap<>();
        float totalDustAvg = 0f;
        for (Map.Entry<String, Map<String, DeviceDataEntity>> entry : siteLastDivecesData.entrySet()) {
            Map<String, DeviceDataEntity> deviceData = entry.getValue();
            float sumDust = 0f;
            float sumRtd = 0f;
            Date dustDate = new Date();
            float sumEffectiveDay = 0f;
            Integer siteId = 0;
            for (Map.Entry<String, DeviceDataEntity> dde : deviceData.entrySet()) {
                if (dde.getValue().getA34011Rtd() == null || dde.getValue().getA34011Day() == null) {
                    continue;
                }
                sumDust += dde.getValue().getA34011Rtd() / 353.25 * 10000 * 30 / dde.getValue().getA34011Day();
                sumRtd += dde.getValue().getA34011Rtd();
                dustDate = dde.getValue().getDataTime();
                sumEffectiveDay += dde.getValue().getA34011Day();
                siteId = dde.getValue().getSiteId();
            }
            totalDustAvg += sumDust / deviceData.size();

            Map<String, Object> avgData = new HashMap<>();
            avgData.put("id", siteId);
            avgData.put("siteName", entry.getKey());
            avgData.put("avgDust", String.format("%.2f", sumDust / deviceData.size()));
            avgData.put("avgRtd", String.format("%.4f", sumRtd / deviceData.size()));
            avgData.put("avgEffectiveDay", String.format("%.1f", sumEffectiveDay / deviceData.size()));
            avgData.put("dataTime", dustDate);
            dustResult.put(entry.getKey(), avgData);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("dustData", dustResult);
        result.put("dustDataAvg", String.format("%.2f", totalDustAvg / siteLastDivecesData.size()));
        return result;
    }

    @Override
    public DeviceDataEntity selectLastMonthData(String mn, Date startTime, Date endTime) {
        return this.baseMapper.selectLastMonthLastData(mn, startTime, endTime);
    }

}
