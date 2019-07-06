package com.modules.generator.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_device_status")
public class DeviceStatus implements Serializable {

    private static final long serialVersionUID = 8509515623508674429L;

    @TableId("id")
    private Integer id;
    private String mn;
    private Date dataTime;
    private Float weight;
    private Float levelX;
    private Float levelY;
    private Float plateTemperature1;
    private Float plateTemperature2;
    private Float plateTemperature3;
    private Float airTemperature;
    private Float waterTemperature;
    private Float exitTemperature;
    private Float exitHumidity;
    private Float entranceTemperature;
    private Float entranceHumidity;
    private Float outTemperature;
    private Float outHumidity;
    private Integer openLimit;
    private Integer closeLimit;
    private Integer doorStatus;
    private Integer up1Limit;
    private Integer up2Limit;
    private Integer down1Limit;
    private Integer down2Limit;
    private Integer trayStatus;
    private Float trayPosition11;
    private Float trayPosition12;
    private Float trayPosition21;
    private Float trayPosition22;
    private Float rainfall;
    private Integer rainfallFlag;
    private Integer bucketTrigger;
    private Integer steamTrigger;
    private Integer balanceFlag;
    private Integer balanceMode;
    private Integer printMode;
    private Integer operation;
    private Integer currentStatus;
    private String troubleSet;
    private Integer siteId;
    private String siteName;
    private String city;
    private Integer status;

    public DeviceStatusHistory convert2DeviceStatusHistory() {
        String json = JSON.toJSONString(this);
        DeviceStatusHistory deviceStatusHistory = JSON.parseObject(json, DeviceStatusHistory.class);
        deviceStatusHistory.setId(null);
        return deviceStatusHistory;
    }

    public DeviceStatusEntity convert2DeviceStatusEntity() {
        DeviceStatusEntity deviceStatusEntity = new DeviceStatusEntity();
        deviceStatusEntity.setMn(mn);
        deviceStatusEntity.setDataTime(dataTime);
        deviceStatusEntity.setOperation(operation);
        deviceStatusEntity.setS10001(levelX);
        deviceStatusEntity.setS10002(levelY);
        deviceStatusEntity.setS10003(plateTemperature1);
        deviceStatusEntity.setS10004(entranceTemperature);
        deviceStatusEntity.setS10005(entranceHumidity);
        deviceStatusEntity.setS10006(outTemperature);
        deviceStatusEntity.setS10007(outHumidity);
        deviceStatusEntity.setS10013((float)doorStatus);
        deviceStatusEntity.setS10014((float)trayStatus);
        deviceStatusEntity.setS10015((float)rainfallFlag);
        deviceStatusEntity.setS10016((float)bucketTrigger);
        deviceStatusEntity.setS10017((float)balanceFlag);
        deviceStatusEntity.setS10018((float)balanceMode);
        return deviceStatusEntity;
    }
}
