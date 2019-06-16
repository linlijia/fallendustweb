package com.modules.generator.pojo;

import com.common.utils.jfreechart.Serie;
import com.deepoove.poi.config.Name;
import com.deepoove.poi.data.DocxRenderData;
import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.modules.generator.entity.SiteEntity;

import java.io.File;
import java.util.*;

/**
 * @author novacaine
 */
public class ReportTemplateVO {
    @Name("ReportDate")
    private Date reportDate;
    @Name("SitePointCount")
    private Integer sitePointCount;
    @Name("SitePoints")
    private MiniTableRenderData sitePoints;
    private DocxRenderData pointSiteAnalysis;
    @Name("MonthlyHistoryData")
    private MiniTableRenderData monthlyHistoryData;
    private String reportFilePath;

    public Integer getSitePointCount() {
        return sitePointCount;
    }

    public void setSitePointCount(Integer sitePointCount) {
        this.sitePointCount = sitePointCount;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public MiniTableRenderData getSitePoints() {
        return sitePoints;
    }

    public void setSitePoints(List<SiteEntity> sitePoints) {
        if (sitePoints == null || sitePoints.size() == 0) {
            this.sitePoints = null;
            return;
        }
        Integer index = 0;
        RowRenderData header = RowRenderData.build(
                new TextRenderData("2B2B2B", "序号"),
                new TextRenderData("2B2B2B", "区县"),
                new TextRenderData("2B2B2B", "降尘仪监测站点"),
                new TextRenderData("2B2B2B", "地址")
        );
        List<RowRenderData> rowRenderData = new ArrayList<>();
        for (SiteEntity sitePoint : sitePoints) {
            index++;
            rowRenderData.add(RowRenderData.build(index.toString(), sitePoint.getDistrict(), sitePoint.getSite(), sitePoint.getAddress()));
        }
        this.sitePoints = new MiniTableRenderData(header, rowRenderData);
        this.setSitePointCount(index);
    }

    public DocxRenderData getPointSiteAnalysis() {
        return pointSiteAnalysis;
    }

    public void setPointSiteAnalysis(List<ReportTemplateSitePointPicVO> reportTemplateSitePointPicVOS) {
        this.pointSiteAnalysis = new DocxRenderData(new File(this.reportFilePath + "/deviceDataHistorytemplate.docx"), reportTemplateSitePointPicVOS);
    }

    public MiniTableRenderData getMonthlyHistoryData() {
        return monthlyHistoryData;
    }

    /**
     * @param days   日期
     * @param values 对应日期的值
     */
    public void setMonthlyHistoryData(String[] days, Map<String, Vector<Serie>> values) {
        Integer index = 0;
        TextRenderData[] textRenderData = new TextRenderData[days.length + 2];
        textRenderData[0] = new TextRenderData("2B2B2B", "序号");
        textRenderData[1] = new TextRenderData("2B2B2B", "降尘仪监测站点");


        for (int i = 0; i < days.length; i++) {
            textRenderData[i + 2] = new TextRenderData("2B2B2B", days[i]);
        }
        RowRenderData header = RowRenderData.build(textRenderData);
        List<RowRenderData> rowRenderData = new ArrayList<>();
        for (Map.Entry<String, Vector<Serie>> sitePoint : values.entrySet()) {
            index++;
            String[] cell = new String[days.length + 2];
            cell[0] = index.toString();
            cell[1] = sitePoint.getKey();
            Vector<Serie> series = sitePoint.getValue();
            for (int i = 0; i < days.length; i++) {
                Object o = sitePoint.getValue().get(0).getData().get(i);
                cell[i + 2] = o == null ? "" : o.toString();
            }
            rowRenderData.add(RowRenderData.build(cell));
        }

        this.monthlyHistoryData = new MiniTableRenderData(header, rowRenderData);
        this.monthlyHistoryData.setWidth(MiniTableRenderData.WIDTH_A4_FULL);
    }

    public void setReportFilePath(String reportFilePath) {
        this.reportFilePath = reportFilePath;
    }
}
