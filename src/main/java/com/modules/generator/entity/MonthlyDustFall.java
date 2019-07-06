package com.modules.generator.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : linli
 * @time : 2019/7/5 13:52
 * @description :
 */

@TableName("t_monthly_dust_fall")
public class MonthlyDustFall implements Serializable {

    private static final long serialVersionUID = 3371268058062791326L;

    @TableId
    private Integer id;
    private String mn;
    private Date dataTime;
    private Integer year;
    private Integer month;
    private Float dustFall;
    private Float weight;
    private Float effectiveDays;
    private Boolean valid;
    private Date storageTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMn() {
        return mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Float getDustFall() {
        return dustFall;
    }

    public void setDustFall(Float dustFall) {
        this.dustFall = dustFall;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getEffectiveDays() {
        return effectiveDays;
    }

    public void setEffectiveDays(Float effectiveDays) {
        this.effectiveDays = effectiveDays;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Date getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(Date storageTime) {
        this.storageTime = storageTime;
    }
}
