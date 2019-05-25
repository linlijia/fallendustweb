package com.modules.generator.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.models.auth.In;
import net.sf.jsqlparser.schema.Column;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@TableName("dust_ops_record")
public class OpsRecordEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Integer id;

    /**
     * 运维图片
     */

    @TableField(exist = false)
    private List<OpsImageEntity> opsImage;

    /**
     *
     */
    private Integer siteId;
    /**
     * 站点名称
     */
    private String siteName;

    /**
     * 运维任务
     */

    private Integer taskId;

    /**
     * 运维人员
     */
    private Integer opsUser;
    /**
     * 运维人员姓名
     */
    private String opsUserName;
    /**
     * 任务类型
     */
    private String taskType;
    /**
     *
     */
    private String address;
    /**
     * 运维时间
     */
    private Date opsTime;
    /**
     * 站点联系人
     */
    private String siteContact;
    /**
     * 联系方式
     */
    private String sitePhoneNum;
    /**
     * 机芯水平
     */
    private Integer movementLevel;
    /**
     * 机芯水平备注
     */
    private String movementLevelNote;
    /**
     * 滤膜更换
     */
    private Integer filterReplacement;
    /**
     * 滤膜更换备注
     */
    private String filterReplacementNote;
    /**
     * 称量仓除尘
     */
    private Integer weighingDust;
    /**
     * 称量仓除尘备注
     */
    private String weighingDustNote;
    /**
     * 外部除尘
     */
    private Integer externalDust;
    /**
     * 外部除尘备注
     */
    private String externalDustNote;
    /**
     * 循环系统除尘
     */
    private Integer circulatorySystemDust;
    /**
     * 循环系统除尘备注
     */
    private String circulatorySystemDustNote;
    /**
     * 储水桶补水
     */
    private Integer waterStorage;
    /**
     * 储水桶补水备注
     */
    private String waterStorageNote;
    /**
     * 部件替换备注
     */
    private String componentReplacementNote;
    /**
     * 部件替换
     */
    private Integer componentReplacement;
    /**
     * 设备外观检查备注
     */
    private String equipmentAppearanceCheckNote;
    /**
     * 设备外观检查
     */
    private Integer equipmentAppearanceCheck;
    /**
     * 离场时设备运行状态
     */
    private Integer deviceStatus;
    /**
     * 离场使设备运行状态备注
     */
    private String deviceStatusNote;
    /**
     * 监测点运维几率单
     */
    private Integer opsRecord;
    /**
     * 监测点运维记录单备注
     */
    private String opsRecordNote;
    /**
     *
     */
    private Date createAt;

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
     * 设置：
     */
    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    /**
     * 获取：
     */
    public Integer getSiteId() {
        return siteId;
    }

    /**
     * 设置：运维人员
     */
    public void setOpsUser(Integer opsUser) {
        this.opsUser = opsUser;
    }

    /**
     * 获取：运维人员
     */
    public Integer getOpsUser() {
        return opsUser;
    }

    /**
     * 设置：
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置：运维时间
     */
    public void setOpsTime(Date opsTime) {
        this.opsTime = opsTime;
    }

    /**
     * 获取：运维时间
     */
    public Date getOpsTime() {
        return opsTime;
    }

    /**
     * 设置：站点联系人
     */
    public void setSiteContact(String siteContact) {
        this.siteContact = siteContact;
    }

    /**
     * 获取：站点联系人
     */
    public String getSiteContact() {
        return siteContact;
    }

    /**
     * 设置：联系方式
     */
    public void setSitePhoneNum(String sitePhoneNum) {
        this.sitePhoneNum = sitePhoneNum;
    }

    /**
     * 获取：联系方式
     */
    public String getSitePhoneNum() {
        return sitePhoneNum;
    }

    /**
     * 设置：机芯水平
     */
    public void setMovementLevel(Integer movementLevel) {
        this.movementLevel = movementLevel;
    }

    /**
     * 获取：机芯水平
     */
    public Integer getMovementLevel() {
        return movementLevel;
    }

    /**
     * 设置：机芯水平备注
     */
    public void setMovementLevelNote(String movementLevelNote) {
        this.movementLevelNote = movementLevelNote;
    }

    /**
     * 获取：机芯水平备注
     */
    public String getMovementLevelNote() {
        return movementLevelNote;
    }

    /**
     * 设置：滤膜更换
     */
    public void setFilterReplacement(Integer filterReplacement) {
        this.filterReplacement = filterReplacement;
    }

    /**
     * 获取：滤膜更换
     */
    public Integer getFilterReplacement() {
        return filterReplacement;
    }

    /**
     * 设置：滤膜更换备注
     */
    public void setFilterReplacementNote(String filterReplacementNote) {
        this.filterReplacementNote = filterReplacementNote;
    }

    /**
     * 获取：滤膜更换备注
     */
    public String getFilterReplacementNote() {
        return filterReplacementNote;
    }

    /**
     * 设置：称量仓除尘
     */
    public void setWeighingDust(Integer weighingDust) {
        this.weighingDust = weighingDust;
    }

    /**
     * 获取：称量仓除尘
     */
    public Integer getWeighingDust() {
        return weighingDust;
    }

    /**
     * 设置：称量仓除尘备注
     */
    public void setWeighingDustNote(String weighingDustNote) {
        this.weighingDustNote = weighingDustNote;
    }

    /**
     * 获取：称量仓除尘备注
     */
    public String getWeighingDustNote() {
        return weighingDustNote;
    }

    /**
     * 设置：外部除尘
     */
    public void setExternalDust(Integer externalDust) {
        this.externalDust = externalDust;
    }

    /**
     * 获取：外部除尘
     */
    public Integer getExternalDust() {
        return externalDust;
    }

    /**
     * 设置：外部除尘备注
     */
    public void setExternalDustNote(String externalDustNote) {
        this.externalDustNote = externalDustNote;
    }

    /**
     * 获取：外部除尘备注
     */
    public String getExternalDustNote() {
        return externalDustNote;
    }

    /**
     * 设置：循环系统除尘
     */
    public void setCirculatorySystemDust(Integer circulatorySystemDust) {
        this.circulatorySystemDust = circulatorySystemDust;
    }

    /**
     * 获取：循环系统除尘
     */
    public Integer getCirculatorySystemDust() {
        return circulatorySystemDust;
    }

    /**
     * 设置：循环系统除尘备注
     */
    public void setCirculatorySystemDustNote(String circulatorySystemDustNote) {
        this.circulatorySystemDustNote = circulatorySystemDustNote;
    }

    /**
     * 获取：循环系统除尘备注
     */
    public String getCirculatorySystemDustNote() {
        return circulatorySystemDustNote;
    }

    /**
     * 设置：储水桶补水
     */
    public void setWaterStorage(Integer waterStorage) {
        this.waterStorage = waterStorage;
    }

    /**
     * 获取：储水桶补水
     */
    public Integer getWaterStorage() {
        return waterStorage;
    }

    /**
     * 设置：储水桶补水备注
     */
    public void setWaterStorageNote(String waterStorageNote) {
        this.waterStorageNote = waterStorageNote;
    }

    /**
     * 获取：储水桶补水备注
     */
    public String getWaterStorageNote() {
        return waterStorageNote;
    }

    /**
     * 设置：部件替换备注
     */
    public void setComponentReplacementNote(String componentReplacementNote) {
        this.componentReplacementNote = componentReplacementNote;
    }

    /**
     * 获取：部件替换备注
     */
    public String getComponentReplacementNote() {
        return componentReplacementNote;
    }

    /**
     * 设置：部件替换
     */
    public void setComponentReplacement(Integer componentReplacement) {
        this.componentReplacement = componentReplacement;
    }

    /**
     * 获取：部件替换
     */
    public Integer getComponentReplacement() {
        return componentReplacement;
    }

    /**
     * 设置：设备外观检查备注
     */
    public void setEquipmentAppearanceCheckNote(String equipmentAppearanceCheckNote) {
        this.equipmentAppearanceCheckNote = equipmentAppearanceCheckNote;
    }

    /**
     * 获取：设备外观检查备注
     */
    public String getEquipmentAppearanceCheckNote() {
        return equipmentAppearanceCheckNote;
    }

    /**
     * 设置：设备外观检查
     */
    public void setEquipmentAppearanceCheck(Integer equipmentAppearanceCheck) {
        this.equipmentAppearanceCheck = equipmentAppearanceCheck;
    }

    /**
     * 获取：设备外观检查
     */
    public Integer getEquipmentAppearanceCheck() {
        return equipmentAppearanceCheck;
    }

    /**
     * 设置：离场时设备运行状态
     */
    public void setDeviceStatus(Integer deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    /**
     * 获取：离场时设备运行状态
     */
    public Integer getDeviceStatus() {
        return deviceStatus;
    }

    /**
     * 设置：离场使设备运行状态备注
     */
    public void setDeviceStatusNote(String deviceStatusNote) {
        this.deviceStatusNote = deviceStatusNote;
    }

    /**
     * 获取：离场使设备运行状态备注
     */
    public String getDeviceStatusNote() {
        return deviceStatusNote;
    }

    /**
     * 设置：监测点运维几率单
     */
    public void setOpsRecord(Integer opsRecord) {
        this.opsRecord = opsRecord;
    }

    /**
     * 获取：监测点运维几率单
     */
    public Integer getOpsRecord() {
        return opsRecord;
    }

    /**
     * 设置：监测点运维记录单备注
     */
    public void setOpsRecordNote(String opsRecordNote) {
        this.opsRecordNote = opsRecordNote;
    }

    /**
     * 获取：监测点运维记录单备注
     */
    public String getOpsRecordNote() {
        return opsRecordNote;
    }

    /**
     * 设置：
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * 获取：
     */
    public Date getCreateAt() {
        return createAt;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getOpsUserName() {
        return opsUserName;
    }

    public void setOpsUserName(String opsUserName) {
        this.opsUserName = opsUserName;
    }

    public List<OpsImageEntity> getOpsImage() {
        return opsImage;
    }

    public void setOpsImage(List<OpsImageEntity> opsImage) {
        this.opsImage = opsImage;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
}
