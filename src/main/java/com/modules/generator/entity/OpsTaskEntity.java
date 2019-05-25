package com.modules.generator.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("dust_ops_task")
public class OpsTaskEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
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
     * 任务分配人
     */
    private Integer assigner;
    /**
     * 分配人姓名
     */
    private String assignerName;
    /**
     * 运维人员
     */
    private String opsUser;
    /**
     * 运维人员姓名
     */
    private String opsUserName;
    /**
     * 任务简介
     */
    private String note;
    /**
     * 任务类型
     */
    private String taskType;

    /**
     * 任务状态
     */
    /**
     * 任务执行日志
     */
    private Date opsDate;

    private String opsStatus;
    /**
     * 运维起始时间
     */
    private Date startTime;
    /**
     * 运维截止时间
     */
    private Date endTime;
    /**
     * 出行方式
     */
    private String travelMode;
    /**
     * 签到时间
     */
    private Date checkIn;
    /**
     * 新建任务时间
     */
    private Date createAt;

    /**
     * 设置：任务ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：任务ID
     */
    public Integer getId() {
        return id;
    }


    /**
     * 设置：任务分配人
     */
    public void setAssigner(Integer assigner) {
        this.assigner = assigner;
    }

    /**
     * 获取：任务分配人
     */
    public Integer getAssigner() {
        return assigner;
    }

    /**
     * 设置：运维人员
     */
    public void setOpsUser(String opsUser) {
        this.opsUser = opsUser;
    }

    /**
     * 获取：运维人员
     */
    public String getOpsUser() {
        return opsUser;
    }

    /**
     * 设置：任务简介
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 获取：任务简介
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置：任务状态
     */
    public void setOpsStatus(String opsStatus) {
        this.opsStatus = opsStatus;
    }

    /**
     * 获取：任务状态
     */
    public String getOpsStatus() {
        return opsStatus;
    }

    /**
     * 设置：运维起始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取：运维起始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置：运维截止时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取：运维截止时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置：出行方式
     */
    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    /**
     * 获取：出行方式
     */
    public String getTravelMode() {
        return travelMode;
    }

    /**
     * 设置：签到时间
     */
    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    /**
     * 获取：签到时间
     */
    public Date getCheckIn() {
        return checkIn;
    }

    /**
     * 设置：新建任务时间
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * 获取：新建任务时间
     */
    public Date getCreateAt() {
        return createAt;
    }

    public Date getOpsDate() {
        return opsDate;
    }

    public void setOpsDate(Date opsDate) {
        this.opsDate = opsDate;
    }

    public String getAssignerName() {
        return assignerName;
    }

    public void setAssignerName(String assignerName) {
        this.assignerName = assignerName;
    }

    public String getOpsUserName() {
        return opsUserName;
    }

    public void setOpsUserName(String opsUserName) {
        this.opsUserName = opsUserName;
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

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
}
