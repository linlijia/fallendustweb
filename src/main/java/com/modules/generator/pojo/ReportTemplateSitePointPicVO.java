package com.modules.generator.pojo;

import com.deepoove.poi.config.Name;
import com.deepoove.poi.data.PictureRenderData;

import java.io.File;

/**
 * @author novacaine
 */
public class ReportTemplateSitePointPicVO {
    private String title;
    @Name("PointAnalysis")
    private PictureRenderData pointAnalysis;
    private String sheet;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PictureRenderData getPointAnalysis() {
        return pointAnalysis;
    }

    public void setPointAnalysis(byte[] imageBytes) {
        this.pointAnalysis = new PictureRenderData(550, 350, ".png", imageBytes);
    }

    public String getSheet() {
        return sheet;
    }

    public void setSheet(String sheet) {
        this.sheet = sheet;
    }
}
