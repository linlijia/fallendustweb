package com.modules.generator.pojo;

import com.modules.generator.entity.MonthlyDustFall;

import java.io.Serializable;

/**
 * @author : linli
 * @time : 2019/7/6 16:10
 * @description :
 */
public class MonthlyDustFallVo extends MonthlyDustFall implements Serializable {

    private static final long serialVersionUID = -2190259800224987525L;

    private Integer siteId;
    private String siteName;

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
