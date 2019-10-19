package com.modules.generator.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.common.utils.DateUtils;
import com.common.utils.PageUtils;
import com.common.utils.R;
import com.modules.generator.entity.DeviceDataEntity;
import com.modules.generator.entity.DeviceStatusExHistory;
import com.modules.generator.pojo.DeviceDataVo;
import com.modules.generator.service.DeviceDataService;
import com.modules.generator.service.DeviceStatusHistoryExExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : linli
 * @since : 2019/10/18 10:39
 */
@RestController
@RequestMapping("generator/devicedetail")
public class DeviceDetailController {

    @Autowired
    private DeviceDataService deviceDataService;
    @Autowired
    private DeviceStatusHistoryExExService deviceStatusHistoryExExService;

    @RequestMapping("/dustfalldata/list")
    public R list(@RequestParam Map<String, Object> params) {
        //增加参数 last=true表示只查最后一条记录
        params.put("sidx", "dataTime");
        params.put("order", "DESC");
        PageUtils page = deviceDataService.queryPage(params);
        List<DeviceDataEntity> dataList = (List<DeviceDataEntity>) page.getList();
        List<DeviceDataVo> deviceDataVoList = new ArrayList<>();
        for (DeviceDataEntity deviceData : dataList) {
            EntityWrapper<DeviceStatusExHistory> wrapper = new EntityWrapper<>();
            String startTime = DateUtils.format(deviceData.getTaskStartTime(), DateUtils.DATE_TIME_PATTERN);
            String endTime = DateUtils.format(deviceData.getTaskEndTime(), DateUtils.DATE_TIME_PATTERN);
            wrapper.where("data_time between \"" + startTime + "\" AND \"" + endTime +"\" AND system_mode = 38")
                    .orderBy(" data_time ").last(" limit 0, 1");
            List<DeviceStatusExHistory> deviceStatusExHistoryList1 = deviceStatusHistoryExExService.selectList(wrapper);

            wrapper = new EntityWrapper<>();
            wrapper.where("data_time between \"" + startTime + "\" AND \"" + endTime +"\" AND system_mode = 38")
                    .orderBy(" data_time DESC ").last(" limit 0, 1");
            List<DeviceStatusExHistory> deviceStatusExHistoryList2 = deviceStatusHistoryExExService.selectList(wrapper);
            if (!deviceStatusExHistoryList1.isEmpty() && !deviceStatusExHistoryList2.isEmpty()) {
                DeviceStatusExHistory startDeviceStatus = deviceStatusExHistoryList1.get(0);
                DeviceStatusExHistory endDeviceStatus = deviceStatusExHistoryList2.get(0);
                DeviceDataVo dataVo = new DeviceDataVo();
                dataVo.setMn(deviceData.getMn());
                dataVo.setDataTime(endDeviceStatus.getDataTime());
                dataVo.setTaskStartTime(startDeviceStatus.getDataTime());
                dataVo.setTaskEndTime(endDeviceStatus.getDataTime());
                dataVo.setWeight(deviceData.getA34011Ori() + deviceData.getA34011Rtd());
                dataVo.setWeightWeight(deviceData.getWeight());
                dataVo.setSampleDays(deviceData.getA34011Day());
                dataVo.setStartInsideTemperature(startDeviceStatus.getMeasuringRoomTemperature());
                dataVo.setStartInsideHumidity(startDeviceStatus.getMeasuringRoomHumidity());
                dataVo.setEndInsideTemperature(endDeviceStatus.getMeasuringRoomTemperature());
                dataVo.setEndInsideHumidity(endDeviceStatus.getMeasuringRoomHumidity());
                dataVo.setStartOutsideTemperature(startDeviceStatus.getEnvironmentTemperature());
                dataVo.setStartOutsideHumidity(startDeviceStatus.getEnvironmentHumidity());
                dataVo.setEndOutsideTemperature(endDeviceStatus.getEnvironmentTemperature());
                dataVo.setEndOutsideHumidity(endDeviceStatus.getEnvironmentHumidity());
                deviceDataVoList.add(dataVo);
            }
        }
        page.setTotalCount(deviceDataVoList.size());
        page.setTotalPage(deviceDataVoList.size() / page.getPageSize());
        page.setList(deviceDataVoList);
        return R.ok().put("page", page);
    }

}
