package com.modules.generator.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : linli
 * @since : 2019/10/18 10:46
 */
@Getter
@Setter
public class DeviceDataVo implements Serializable {

    private static final long serialVersionUID = -8353052311779989218L;

    private Integer id;
    private Date dataTime;
    private String mn;
    private float weight;
    private float weightWeight;
    private float sampleDays;
    private float startInsideTemperature;
    private float startInsideHumidity;
    private float endInsideTemperature;
    private float endInsideHumidity;
    private float startOutsideTemperature;
    private float startOutsideHumidity;
    private float endOutsideTemperature;
    private float endOutsideHumidity;
    private Date taskStartTime;
    private Date taskEndTime;

}
