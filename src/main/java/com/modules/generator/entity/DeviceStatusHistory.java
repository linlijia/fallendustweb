package com.modules.generator.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_device_status_history")
public class DeviceStatusHistory implements Serializable {

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
}
