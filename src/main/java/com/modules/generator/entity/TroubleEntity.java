package com.modules.generator.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("dust_trouble")
public class TroubleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Integer id;
    private Date happenTime;
    private String mn;
    private Integer troubleCode;
    private String troubleDescription;
    private boolean solved;
    private Date solvedTime;
    private Integer solvedMethod;
    private Integer solvedShooter;

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

    public void setTroubleCode(Integer troubleCode) {
        this.troubleCode = troubleCode;
    }

    public String getTroubleDescription() {
        return troubleDescription;
    }

    public void setTroubleDescription(String troubleDescription) {
        this.troubleDescription = troubleDescription;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
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

    public Integer getSolvedShooter() {
        return solvedShooter;
    }

    public void setSolvedShooter(Integer solvedShooter) {
        this.solvedShooter = solvedShooter;
    }
}
