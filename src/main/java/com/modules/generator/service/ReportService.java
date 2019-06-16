package com.modules.generator.service;

import com.modules.generator.entity.SiteEntity;
import com.modules.generator.pojo.ReportTemplateVO;

import java.util.Date;
import java.util.List;

/**
 * @author novacaine
 */
public interface ReportService {
    List<SiteEntity> getSitePoint();

    ReportTemplateVO generateTpl(Date start, Date end, String city);
}
