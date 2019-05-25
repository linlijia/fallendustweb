package com.modules.generator.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

@TableName("dust_device_data")
public class DeviceDataEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Integer id;
    /**
     * 请求编码(QN)
     */
    private String qn;
    /**
     * 站点
     */
    private Integer siteId;
    private String siteName;
    private String city;
    /**
     * 设备编码(MN)
     */
    private String mn;
    /**
     * 系统编码(ST)
     */
    private String st;
    /**
     * 命令编码(CN)
     */
    private String cn;
    /**
     * 密码(PW)
     */
    private String pw;
    /**
     * 拆分包应答标识(FLAG)
     */
    private Integer flag;
    /**
     * 指令参数(CP)
     */
    private String cp;
    /**
     * 数据时间
     */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataTime;
    /**
     * 降尘实时数据(a34011-rtd)
     */
    private Float a34011Rtd;
    /**
     * 降尘原始数据(a34011-ori)
     */
    private Float a34011Ori;
    /**
     * 降尘数据标识位(a34011-ori)
     */

    @TableField(exist = false)
    private String dustData;

    private String a34011Flag;
    /**
     * 有效集尘天数
     */
    private Float a34011Day;


    /**
     * 设置：ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：请求编码(QN)
     */
    public void setQn(String qn) {
        this.qn = qn;
    }

    /**
     * 获取：请求编码(QN)
     */
    public String getQn() {
        return qn;
    }

    /**
     * 设置：设备编码(MN)
     */
    public void setMn(String mn) {
        this.mn = mn;
    }

    /**
     * 获取：设备编码(MN)
     */
    public String getMn() {
        return mn;
    }

    /**
     * 设置：系统编码(ST)
     */
    public void setSt(String st) {
        this.st = st;
    }

    /**
     * 获取：系统编码(ST)
     */
    public String getSt() {
        return st;
    }

    /**
     * 设置：命令编码(CN)
     */
    public void setCn(String cn) {
        this.cn = cn;
    }

    /**
     * 获取：命令编码(CN)
     */
    public String getCn() {
        return cn;
    }

    /**
     * 设置：密码(PW)
     */
    public void setPw(String pw) {
        this.pw = pw;
    }

    /**
     * 获取：密码(PW)
     */
    public String getPw() {
        return pw;
    }

    /**
     * 设置：拆分包应答标识(FLAG)
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * 获取：拆分包应答标识(FLAG)
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * 设置：指令参数(CP)
     */
    public void setCp(String cp) {
        this.cp = cp;
    }

    /**
     * 获取：指令参数(CP)
     */
    public String getCp() {
        return cp;
    }

    /**
     * 设置：数据时间
     */
    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    /**
     * 获取：数据时间
     */
    public Date getDataTime() {
        return dataTime;
    }

    /**
     * 设置：降尘实时数据(a34011-rtd)
     */
    public void setA34011Rtd(Float a34011Rtd) {
        this.a34011Rtd = a34011Rtd;
    }

    /**
     * 获取：降尘实时数据(a34011-rtd)
     */
    public Float getA34011Rtd() {
        return a34011Rtd;
    }

    /**
     * 设置：降尘原始数据(a34011-ori)
     */
    public void setA34011Ori(Float a34011Ori) {
        this.a34011Ori = a34011Ori;
    }

    /**
     * 获取：降尘原始数据(a34011-ori)
     */
    public Float getA34011Ori() {
        return a34011Ori;
    }

    /**
     * 设置：降尘数据标识位(a34011-ori)
     */
    public void setA34011Flag(String a34011Flag) {
        this.a34011Flag = a34011Flag;
    }

    /**
     * 获取：降尘数据标识位(a34011-ori)
     */
    public String getA34011Flag() {
        return a34011Flag;
    }

    public Float getA34011Day() {
        return a34011Day;
    }

    public void setA34011Day(Float a34011Day) {
        this.a34011Day = a34011Day;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDustData() {
        return dustData;
    }

    public void setDustData(String dustData) {
        this.dustData = dustData;
    }
}
