package com.modules.generator.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("dust_device")
public class DeviceEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Integer id;
    /**
     * 站点ID
     */
    private Integer siteId;
    /**
     * 站点名称
     */
    private String siteName;
    /**
     * 城市
     */
    private String city;
    /**
     * MN码
     */
    private String mn;
    /**
     * 接口根路径
     */
    private String interfacePath;
    /**
     * 服务端信息
     */
    private String serviceInfo;
    /**
     * 服务端密码
     */
    private String passwd;
    /**
     * 系统类型
     */
    private String systemType;
    /**
     * 串口名
     */
    private String serialPortType;
    /**
     * 温度湿度限制
     */
    private String temperatureHumidityLimit;
    /**
     * 称量间隔（小时）
     */
    private Integer uploadInterval;
    /**
     * 添加时间
     */
    private Date createAt;
    /**
     * 状态
     */
    private Integer operation;
    /**
     * 最后上传数据时间
     */
    private Date lastUploadData;

    /**
     * 任务启动小时
     */
    private Integer taskHour;
    /**
     * 任务启动分钟
     */
    private Integer taskMinute;

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
     * 设置：站点ID
     */
    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    /**
     * 获取：站点ID
     */
    public Integer getSiteId() {
        return siteId;
    }

    /**
     * 设置：MN码
     */
    public void setMn(String mn) {
        this.mn = mn;
    }

    /**
     * 获取：MN码
     */
    public String getMn() {
        return mn;
    }

    /**
     * 设置：接口根路径
     */
    public void setInterfacePath(String interfacePath) {
        this.interfacePath = interfacePath;
    }

    /**
     * 获取：接口根路径
     */
    public String getInterfacePath() {
        return interfacePath;
    }

    /**
     * 设置：服务端信息
     */
    public void setServiceInfo(String serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    /**
     * 获取：服务端信息
     */
    public String getServiceInfo() {
        return serviceInfo;
    }

    /**
     * 设置：服务端密码
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /**
     * 获取：服务端密码
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * 设置：系统类型
     */
    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    /**
     * 获取：系统类型
     */
    public String getSystemType() {
        return systemType;
    }

    /**
     * 设置：串口名
     */
    public void setSerialPortType(String serialPortType) {
        this.serialPortType = serialPortType;
    }

    /**
     * 获取：串口名
     */
    public String getSerialPortType() {
        return serialPortType;
    }

    /**
     * 设置：温度湿度限制
     */
    public void setTemperatureHumidityLimit(String temperatureHumidityLimit) {
        this.temperatureHumidityLimit = temperatureHumidityLimit;
    }

    /**
     * 获取：温度湿度限制
     */
    public String getTemperatureHumidityLimit() {
        return temperatureHumidityLimit;
    }

    /**
     * 设置：称量间隔（小时）
     */
    public void setUploadInterval(Integer uploadInterval) {
        this.uploadInterval = uploadInterval;
    }

    /**
     * 获取：称量间隔（小时）
     */
    public Integer getUploadInterval() {
        return uploadInterval;
    }

    /**
     * 设置：添加时间
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * 获取：添加时间
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * 设置：状态
     */
    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    /**
     * 获取：状态
     */
    public Integer getOperation() {
        return operation;
    }


    public Date getLastUploadData() {
        return lastUploadData;
    }

    public void setLastUploadData(Date lastUploadData) {
        this.lastUploadData = lastUploadData;
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

    public Integer getTaskHour() {
        return taskHour;
    }

    public void setTaskHour(Integer taskHour) {
        this.taskHour = taskHour;
    }

    public Integer getTaskMinute() {
        return taskMinute;
    }

    public void setTaskMinute(Integer taskMinute) {
        this.taskMinute = taskMinute;
    }
}
