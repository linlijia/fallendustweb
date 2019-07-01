package com.modules.generator.pojo;

public class TroubleRankingVO {
    private String siteName;
    private Integer offline;
    private Integer breakdown;
    private Integer total;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Integer getOffline() {
        return offline;
    }

    public void setOffline(Integer offline) {
        this.offline = offline;
    }

    public Integer getBreakdown() {
        return breakdown;
    }

    public void setBreakdown(Integer breakdown) {
        this.breakdown = breakdown;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
