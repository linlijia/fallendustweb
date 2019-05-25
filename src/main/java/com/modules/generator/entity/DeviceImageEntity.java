package com.modules.generator.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("dust_device_image")
public class DeviceImageEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Integer id;
    /**
     * 设备mn
     */
    private String mn;
    /**
     * 图片路径
     */
    private String path;
    /**
     * 上传时间
     */
    private Date dateTime;
    /**
     * 站点ID
     */
    private Integer siteId;
    private String siteName;

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
     * 设置：设备mn
     */
    public void setMn(String mn) {
        this.mn = mn;
    }

    /**
     * 获取：设备mn
     */
    public String getMn() {
        return mn;
    }

    /**
     * 设置：图片路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取：图片路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置：上传时间
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * 获取：上传时间
     */
    public Date getDateTime() {
        return dateTime;
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
}
