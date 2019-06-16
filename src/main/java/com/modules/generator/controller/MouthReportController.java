package com.modules.generator.controller;

import com.common.utils.DateUtils;
import com.common.utils.DownloadUtil;
import com.common.utils.PageUtils;
import com.common.utils.R;
import com.deepoove.poi.XWPFTemplate;
import com.modules.generator.entity.MouthReportEntity;
import com.modules.generator.pojo.ReportTemplateVO;
import com.modules.generator.service.MouthReportService;
import com.modules.generator.service.ReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;


/**
 * @author novacaine
 * @email superman@gmail.com
 * @date 2019-06-16 19:24:39
 */
@RestController
@RequestMapping("generator/mouthreport")
public class MouthReportController {
    @Autowired
    private MouthReportService mouthReportService;
    @Autowired
    private ReportService reportService;
    @Value("${template}")
    private String template;
    @Value("${report-file-path}")
    private String reportFilePath;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:mouthreport:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = mouthReportService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:mouthreport:info")
    public R info(@PathVariable("id") Integer id) {
        MouthReportEntity mouthReport = mouthReportService.selectById(id);

        return R.ok().put("mouthReport", mouthReport);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:mouthreport:save")
    public R save(@RequestBody MouthReportEntity mouthReport) {
        String month = mouthReport.getMouth();
        String city = "上海市";
        if (month == null || "".equals(month.trim())) {
            return R.error(400, "必须选择年月");
        }
        if (city == null || "".equals(city.trim())) {
            city = "上海市";
        }

        Date start = DateUtils.stringToDate(month, DateUtils.MONTH_PATTERN);
        Date end = DateUtils.addDateMonths(start, 1);
        ReportTemplateVO reportTemplateVO = reportService.generateTpl(start, end, city);
        XWPFTemplate template = XWPFTemplate.compile(this.template).render(reportTemplateVO);
        String path = reportFilePath;
        File fileFold = new File(path);
        if (!fileFold.exists()) {
            fileFold.mkdirs();
        }
        String fullPath = path + "/" + month + ".docx";
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fullPath);
            template.write(out);
            out.flush();
            out.close();
            template.close();
        } catch (IOException e) {
            e.printStackTrace();
            return R.error(500, "保存文件失败");
        }
        mouthReport.setFilePath(fullPath);
        mouthReportService.insert(mouthReport);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:mouthreport:update")
    public R update(@RequestBody MouthReportEntity mouthReport) {
        mouthReportService.updateById(mouthReport);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:mouthreport:delete")
    public R delete(@RequestBody Integer[] ids) {
        mouthReportService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 下载
     */
    @RequestMapping("/download/{id}")
    public void download(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        MouthReportEntity mouthReportEntity = mouthReportService.selectById(id);
        String fullPath = mouthReportEntity.getFilePath();
        DownloadUtil.download(request, response, fullPath);
    }

}
