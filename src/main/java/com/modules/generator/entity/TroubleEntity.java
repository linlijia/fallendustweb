package com.modules.generator.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.modules.generator.Enums;

import java.io.Serializable;
import java.util.Date;

/**
 * @author novacaine
 * @email superman@gmail.com
 * @date 2019-06-23 23:54:50
 */
@TableName("dust_trouble")
public class TroubleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date happenTime;
    /**
     *
     */
    private String mn;
    /**
     *
     */
    private Integer troubleCode;
    @TableField(exist = false)
    private String troublCodeName;
    /**
     *
     */
    private String troubleDescription;
    /**
     *
     */
    private Boolean solved;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date solvedTime;
    /**
     *
     */
    private Integer solvedMethod;
    /**
     * 故障解决人员（0：表示前端系统自己恢复）
     */
    private Integer troubleShooter;

    private void setTroubleCode(Integer troubleCode) {
        this.troubleCode = troubleCode;
        if (troubleCode != null) {
            for (Enums.TroubleType v : Enums.TroubleType.values()) {
                if (troubleCode == v.getCode()) {
                    this.troublCodeName = v.getTroubleName();
                }
            }
        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(Date happenTime) {
        this.happenTime = happenTime;
    }

    public String getMn() {
        return mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public Integer getTroubleCode() {
        return troubleCode;
    }

    public String getTroublCodeName() {
        return troublCodeName;
    }

    public void setTroublCodeName(String troublCodeName) {
        this.troublCodeName = troublCodeName;
    }

    public String getTroubleDescription() {
        return troubleDescription;
    }

    public void setTroubleDescription(String troubleDescription) {
        this.troubleDescription = troubleDescription;
    }

    public Boolean getSolved() {
        return solved;
    }

    public void setSolved(Boolean solved) {
        this.solved = solved;
    }

    public Date getSolvedTime() {
        return solvedTime;
    }

    public void setSolvedTime(Date solvedTime) {
        this.solvedTime = solvedTime;
    }

    public Integer getSolvedMethod() {
        return solvedMethod;
    }

    public void setSolvedMethod(Integer solvedMethod) {
        this.solvedMethod = solvedMethod;
    }

    public Integer getTroubleShooter() {
        return troubleShooter;
    }

    public void setTroubleShooter(Integer troubleShooter) {
        this.troubleShooter = troubleShooter;
    }
}
