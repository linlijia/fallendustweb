package com.modules.generator.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 程序更新
 *
 * @author novacaine
 * @email xxxxx@gmail.com
 * @date 2019-03-29 08:11:49
 */
@TableName("dust_app_update")
public class AppUpdateEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 适用设备的型号
     */
    private String model;
    /**
     * 版本
     */
    private String version;
    /**
     * 上传时间
     */
    private Date createAt;
    /**
     * 文件路径
     */
    private String path;
    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：应用名称
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * 获取：应用名称
     */
    public String getAppName() {
        return appName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 设置：版本
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 获取：版本
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置：上传时间
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * 获取：上传时间
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * 设置：文件路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取：文件路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置：文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取：文件名称
     */
    public String getFileName() {
        return fileName;
    }
}
