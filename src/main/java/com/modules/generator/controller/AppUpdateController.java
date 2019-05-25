package com.modules.generator.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.common.exception.RRException;
import com.common.utils.DateUtils;
import com.common.utils.PageUtils;
import com.common.utils.R;
import com.modules.generator.entity.AppUpdateEntity;
import com.modules.generator.service.AppUpdateService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 程序更新
 *
 * @author novacaine
 * @email xxxxx@gmail.com
 * @date 2019-03-29 08:11:49
 */
@RestController
@RequestMapping("generator/appupdate")
public class AppUpdateController {
    @Autowired
    private AppUpdateService appUpdateService;
    @Value("${upload-file-path}")
    private String uploadPath;
    @Value("${file-server}")
    private String fileServer;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:appupdate:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = appUpdateService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:appupdate:info")
    public R info(@PathVariable("id") Integer id) {
        AppUpdateEntity appUpdate = appUpdateService.selectById(id);

        return R.ok().put("appUpdate", appUpdate);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:appupdate:save")
    public R save(@RequestBody AppUpdateEntity appUpdate) {
        appUpdate.setFileName(appUpdate.getPath() == null ? "" : appUpdate.getPath().
                substring(appUpdate.getPath().lastIndexOf("/") + 1, appUpdate.getPath().length()));
        appUpdateService.insert(appUpdate);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:appupdate:update")
    public R update(@RequestBody AppUpdateEntity appUpdate) {
        appUpdate.setFileName(appUpdate.getPath() == null ? "" : appUpdate.getPath().
                substring(appUpdate.getPath().lastIndexOf("/") + 1, appUpdate.getPath().length()));
        appUpdateService.updateById(appUpdate);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:appupdate:delete")
    public R delete(@RequestBody Integer[] ids) {
        appUpdateService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }

        String dateTime = DateUtils.format(new Date(), DateUtils.DATE_PATTERN);

        String path = uploadPath + "files/" + "app_file/";
        File target = new File(path);
        if (!target.exists()) {
            target.mkdirs();
        }

        String filename = file.getOriginalFilename();
        File newFile = new File(path, filename);
        file.transferTo(newFile);
        return R.ok().put("path", "/files/" + "app_file/" + filename);
    }

    @RequestMapping("/download/last")
    public void getDownload(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
        // Get your file stream from wherever.
        EntityWrapper<AppUpdateEntity> entityEntityWrapper = new EntityWrapper();
        entityEntityWrapper.orderBy("id", false);
        List<AppUpdateEntity> appUpdateEntity = appUpdateService.selectList(entityEntityWrapper);
        if (appUpdateEntity == null || appUpdateEntity.size() == 0) {
            throw new FileNotFoundException("please upload app file first");
        }
        String fullPath = uploadPath + appUpdateEntity.get(0).getPath();

        File downloadFile = new File(fullPath);

        ServletContext context = request.getServletContext();
        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
        response.setHeader("Accept-Ranges", "bytes");
        long downloadSize = downloadFile.length();
        long fromPos = 0, toPos = 0;
        if (request.getHeader("Range") == null) {
            response.setHeader("Content-Length", downloadSize + "");
        } else {
            // 若客户端传来Range，说明之前下载了一部分，设置206状态(SC_PARTIAL_CONTENT)
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            String range = request.getHeader("Range");
            String bytes = range.replaceAll("bytes=", "");
            String[] ary = bytes.split("-");
            fromPos = Long.parseLong(ary[0]);
            if (ary.length == 2) {
                toPos = Long.parseLong(ary[1]);
            }
            int size;
            if (toPos > fromPos) {
                size = (int) (toPos - fromPos);
            } else {
                size = (int) (downloadSize - fromPos);
            }
            response.setHeader("Content-Length", size + "");
            downloadSize = size;
        }
        RandomAccessFile in = null;
        OutputStream out = null;
        try {
            in = new RandomAccessFile(downloadFile, "rw");
            if (fromPos > 0) {
                in.seek(fromPos);
            }
            int bufLen = (int) (downloadSize < 2048 ? downloadSize : 2048);
            byte[] buffer = new byte[bufLen];
            int num;
            int count = 0;
            out = response.getOutputStream();
            while ((num = in.read(buffer)) != -1) {
                out.write(buffer, 0, num);
                count += num;
                if (downloadSize - count < bufLen) {
                    bufLen = (int) (downloadSize - count);
                    if (bufLen == 0) {
                        break;
                    }
                    buffer = new byte[bufLen];
                }
            }
            response.flushBuffer();
        } catch (IOException e) {
            System.out.println("数据被暂停或中断。");
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.out.println("数据被暂停或中断。");
                    e.printStackTrace();
                }
            }
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println("数据被暂停中断。");
                    e.printStackTrace();
                }
            }
        }
    }
}
