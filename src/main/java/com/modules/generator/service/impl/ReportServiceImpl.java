package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.common.utils.DateUtils;
import com.common.utils.jfreechart.BarChart;
import com.common.utils.jfreechart.JfreeChartUtils;
import com.common.utils.jfreechart.Serie;
import com.modules.generator.entity.DeviceDataEntity;
import com.modules.generator.entity.SiteEntity;
import com.modules.generator.pojo.ReportTemplateSitePointPicVO;
import com.modules.generator.pojo.ReportTemplateVO;
import com.modules.generator.service.DeviceDataService;
import com.modules.generator.service.ReportService;
import com.modules.generator.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author novacaine
 */
@Service("reportService")
public class ReportServiceImpl implements ReportService {
    @Autowired
    private SiteService siteService;
    @Autowired
    private DeviceDataService deviceDataService;
    @Value("${report-file-path}")
    private String reportFilePath;

    @Override
    public List<SiteEntity> getSitePoint() {
        return siteService.selectList(null);
    }


    @Override
    public ReportTemplateVO generateTpl(Date start, Date end, String city) {
        ReportTemplateVO reportTemplateVO = new ReportTemplateVO();
        reportTemplateVO.setReportFilePath(reportFilePath);
        reportTemplateVO.setReportDate(start);
        List<SiteEntity> sitePoint = this.getSitePoint();
        reportTemplateVO.setSitePoints(sitePoint);
        List<ReportTemplateSitePointPicVO> reportTemplateSitePointPicVOS = new ArrayList<ReportTemplateSitePointPicVO>();

        Map.Entry<String[], Map<String, Vector<Serie>>> pair = deviceDataHistory(start, end, city);


        //生成点位数据分析图
        Map<String, byte[]> images = generateSitePointImage(pair);
        String[] sites = new String[images.size()];
        images.keySet().toArray(sites);
        Arrays.sort(sites);
        Integer titleIndex = 1;
        Integer sheetIndex = 5;
        for (String site : sites) {
            ReportTemplateSitePointPicVO reportTemplateSitePointPicVO = new ReportTemplateSitePointPicVO();
            reportTemplateSitePointPicVO.setTitle("5.7." + titleIndex.toString() + " " + site);
            reportTemplateSitePointPicVO.setPointAnalysis(images.get(site));
            reportTemplateSitePointPicVO.setSheet("5-" + sheetIndex.toString() + ": " + site);
            reportTemplateSitePointPicVOS.add(reportTemplateSitePointPicVO);
            titleIndex++;
            sheetIndex++;
        }
        //生成附录：月基础数据表
        reportTemplateVO.setMonthlyHistoryData(pair.getKey(), pair.getValue());

        reportTemplateVO.setPointSiteAnalysis(reportTemplateSitePointPicVOS);
        return reportTemplateVO;
    }


    /**
     * 生成点位数据分析图
     *
     * @param pair key为日期范围，value为每个站点的每日数据
     * @return
     */
    public Map<String, byte[]> generateSitePointImage(Map.Entry<String[], Map<String, Vector<Serie>>> pair) {
        Map<String, byte[]> result = new HashMap<>();

        String[] categories = pair.getKey();
        Map<String, Vector<Serie>> datas = pair.getValue();
        for (Map.Entry<String, Vector<Serie>> sitePointData : datas.entrySet()) {
            BarChart barChart = new BarChart();
            byte[] imageBytes = barChart.createByteChart("月降尘数据",
                    "称量日期", "降尘量：g（T/k㎡*m）", 550, 350,
                    JfreeChartUtils.createDefaultCategoryDataset(sitePointData.getValue(), categories));
            result.put(sitePointData.getKey(), imageBytes);
        }
        return result;
    }


    /**
     * @param start 开始日期
     * @param end   结束日期
     * @param city  城市
     * @return pair key为日期范围，value为每个站点的每日数据
     */
    public Map.Entry<String[], Map<String, Vector<Serie>>> deviceDataHistory(Date start, Date end, String city) {
        Map<String, Vector<Serie>> result = new HashMap<>();

        EntityWrapper<DeviceDataEntity> ew = new EntityWrapper();
        ew.gt("data_time", start);
        ew.lt("data_time", end);
        ew.eq("city", city);
        String[] categories = DateUtils.getRangeDays(DateUtils.format(start, DateUtils.DATE_PATTERN), DateUtils.format(end
                , DateUtils.DATE_PATTERN));
        List<DeviceDataEntity> mouthlyData = deviceDataService.selectList(ew);
        Map<String, Map<String, Float>> dataSeperate = new HashMap<>();
        for (DeviceDataEntity dataEntity : mouthlyData) {
            if (dataSeperate.get(dataEntity.getSiteName()) == null) {
                dataSeperate.put(dataEntity.getSiteName(), new HashMap());
            }
            Map<String, Float> data = dataSeperate.get(dataEntity.getSiteName());
            data.put(DateUtils.format(dataEntity.getDataTime(), DateUtils.DATE_PATTERN), dataEntity.getA34011Rtd());
        }

        for (Map.Entry<String, Map<String, Float>> ds : dataSeperate.entrySet()) {
            //每个站点一张图
            Vector<Serie> series = new Vector<>();
            Vector<Object> values = new Vector<>();
            Serie serie = new Serie();
            serie.setName(ds.getKey());
            for (String c : categories) {
                values.add(ds.getValue().get(c));
            }
            serie.setData(values);
            series.add(serie);
            result.put(ds.getKey(), series);
        }
        Map<String[], Map<String, Vector<Serie>>> m = new HashMap();
        m.put(categories, result);
        return (Map.Entry<String[], Map<String, Vector<Serie>>>) m.entrySet().toArray()[0];

    }

}
