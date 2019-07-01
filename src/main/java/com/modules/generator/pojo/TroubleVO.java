package com.modules.generator.pojo;

import com.modules.generator.entity.TroubleEntity;

public class TroubleVO extends TroubleEntity {
    private String siteName;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}
