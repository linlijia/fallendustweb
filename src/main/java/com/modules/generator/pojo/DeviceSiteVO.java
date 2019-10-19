package com.modules.generator.pojo;

import com.modules.generator.entity.DeviceEntity;

import java.io.Serializable;
import java.util.Date;

public class DeviceSiteVO extends DeviceEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSuperiorSite() {
        return superiorSite;
    }

    public void setSuperiorSite(String superiorSite) {
        this.superiorSite = superiorSite;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getLatidute() {
        return latidute;
    }

    public void setLatidute(Float latidute) {
        this.latidute = latidute;
    }

    public Float getLongidute() {
        return longidute;
    }

    public void setLongidute(Float longidute) {
        this.longidute = longidute;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    /**
     * ID
     */
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
     * 站点
     */
    private String site;

    /**
     * 城市
     */
    private String city;
    /**
     * 区域
     */
    private String district;
    /**
     * 国家站点
     */
    private String superiorSite;
    /**
     * 地址
     */
    private String address;
    /**
     * 经度
     */
    private Float latidute;
    /**
     * 纬度
     */
    private Float longidute;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 联系电话
     */
    private String phoneNo;

    @Override
    public String getMn() {
        return mn;
    }

    @Override
    public void setMn(String mn) {
        this.mn = mn;
    }

    @Override
    public Integer getSiteId() {
        return siteId;
    }

    @Override
    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    @Override
    public String getSiteName() {
        return siteName;
    }

    @Override
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}
