package com.modules.generator.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

@TableName("dust_site")
public class SiteEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Integer id;
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
    private BigDecimal latidute;
    /**
     * 纬度
     */
    private BigDecimal longidute;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 联系电话
     */
    private String phoneNo;

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
     * 设置：站点
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * 获取：站点
     */
    public String getSite() {
        return site;
    }

    /**
     * 设置：区域
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * 获取：区域
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 设置：国家站点
     */
    public void setSuperiorSite(String superiorSite) {
        this.superiorSite = superiorSite;
    }

    /**
     * 获取：国家站点
     */
    public String getSuperiorSite() {
        return superiorSite;
    }

    /**
     * 设置：地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置：经度
     */
    public void setLatidute(BigDecimal latidute) {
        this.latidute = latidute;
    }

    /**
     * 获取：经度
     */
    public BigDecimal getLatidute() {
        return latidute;
    }

    /**
     * 设置：纬度
     */
    public void setLongidute(BigDecimal longidute) {
        this.longidute = longidute;
    }

    /**
     * 获取：纬度
     */
    public BigDecimal getLongidute() {
        return longidute;
    }

    /**
     * 设置：联系人
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * 获取：联系人
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置：联系电话
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * 获取：联系电话
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
