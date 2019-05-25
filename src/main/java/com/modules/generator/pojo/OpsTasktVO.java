package com.modules.generator.pojo;

import com.modules.generator.entity.OpsTaskEntity;
import com.modules.generator.entity.SiteEntity;
import com.modules.sys.entity.SysUserEntity;

import java.io.Serializable;
import java.util.List;

public class OpsTasktVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private OpsTaskEntity opsTask;
    private List<SiteEntity> sites;
    private List<SysUserEntity> opsUsers;

    public OpsTaskEntity getOpsTask() {
        return opsTask;
    }

    public void setOpsTask(OpsTaskEntity opsTask) {
        this.opsTask = opsTask;
    }

    public List<SiteEntity> getSites() {
        return sites;
    }

    public void setSites(List<SiteEntity> sites) {
        this.sites = sites;
    }

    public List<SysUserEntity> getOpsUsers() {
        return opsUsers;
    }

    public void setOpsUsers(List<SysUserEntity> opsUsers) {
        this.opsUsers = opsUsers;
    }
}
