package com.modules.generator.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author linli
 */
@Data
@TableName("t_device_status_ex_history")
public class DeviceStatusExHistory implements Serializable {

    private static final long serialVersionUID = 8509515623508674429L;

    @TableId("id")
    private Integer id;
    private String mn;
    private Date dataTime;
    private Float realTimeWeight;
    private Float plateTemperature1;
    private Float plateTemperature2;
    private Float controlBoardTemperature;
    private Float measuringRoomTemperature;
    private Float measuringRoomHumidity;
    private Float environmentTemperature;
    private Float environmentHumidity;
    private Integer doorOpenLimit;
    private Integer doorClosedLimit;
    private Integer hotPlateUpLimit;
    private Integer hotPlateDownLimit;
    private Integer tubuleUpLimit;
    private Integer tubuleDownLimit;
    private Float potentiometer1;
    private Float potentiometer2;
    private Integer pitcherFullLimit;
    private Integer pitcherLowerLimit;
    private Integer rainfall;
    private Integer frontDoorPosition;
    private Integer backDoorPosition;
    private Integer dataAvailable;
    private Integer operationFlag;
    private Float bucketWeight;
    private Float weightWeight;
    private Integer systemMode;
    private String errorCode;
    private Integer operation;
    private Integer currentStatus;
    private Integer currentPeriod;
    private String troubleSet;
    private Integer siteId;
    private String siteName;
    private String city;
}
