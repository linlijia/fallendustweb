package com.modules.job.task;

import com.modules.generator.entity.DeviceDataEntity;
import com.modules.generator.entity.DeviceEntity;
import com.modules.generator.entity.MonthlyDustFall;
import com.modules.generator.service.DeviceDataService;
import com.modules.generator.service.DeviceService;
import com.modules.generator.service.MonthlyDustFallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author : linli
 * @time : 2019/7/6 9:45
 * @description :
 */
@Component("monthlyDustFallTask")
public class MonthlyDustFallTask {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceDataService deviceDataService;
    @Autowired
    private MonthlyDustFallService monthlyDustFallService;

    public void calculateMonthlyDustFall() {
        List<DeviceEntity> deviceList = deviceService.selectList(null);
        List<MonthlyDustFall> monthlyDustFallList = new ArrayList<>();
        for (DeviceEntity device : deviceList) {
            MonthlyDustFall monthlyDustFall = new MonthlyDustFall();
            monthlyDustFall.setMn(device.getMn());
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            monthlyDustFall.setStorageTime(date);
            calendar.add(Calendar.MONTH, -1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date startTime = calendar.getTime();
            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.SECOND, -1);
            DeviceDataEntity deviceData = deviceDataService.selectLastMonthData(device.getMn(), startTime, calendar.getTime());
            monthlyDustFall.setEffectiveDays(deviceData.getA34011Day());
            Float dustFall = deviceData.getA34011Rtd() / 353.25F * 10000 * 30 / deviceData.getA34011Day();
            monthlyDustFall.setDustFall(dustFall);
            monthlyDustFall.setWeight(deviceData.getA34011Rtd());
            monthlyDustFall.setDataTime(calendar.getTime());
            monthlyDustFall.setYear(calendar.get(Calendar.YEAR));
            monthlyDustFall.setMonth(calendar.get(Calendar.MONTH));
            monthlyDustFall.setValid(monthlyDustFall.getEffectiveDays() > 28);
            monthlyDustFallList.add(monthlyDustFall);
        }
        monthlyDustFallService.insertBatch(monthlyDustFallList);
    }
}
