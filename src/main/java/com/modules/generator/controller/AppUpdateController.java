package com.modules.generator.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.common.exception.RRException;
import com.common.utils.DateUtils;
import com.common.utils.DownloadUtil;
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
import java.util.*;


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
        List<AppUpdateEntity> list = appUpdateService.selectBatchIds(Arrays.asList(ids));
        List<Integer> deleteList = new ArrayList<>();
        for (AppUpdateEntity entity : list) {
            boolean result = new File(uploadPath + entity.getPath()).delete();
            if (result) {
                deleteList.add(entity.getId());
            }
        }
        appUpdateService.deleteBatchIds(deleteList);
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
        DownloadUtil.download(request, response, fullPath);
    }

    @GetMapping("/downloadex/{model}")
    public void getDownload(@PathVariable("model") String model, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
        // Get your file stream from wherever.
        EntityWrapper<AppUpdateEntity> entityEntityWrapper = new EntityWrapper();
        entityEntityWrapper.where("model = \"" + model + "\"");
        entityEntityWrapper.orderBy("id", false);
        List<AppUpdateEntity> appUpdateEntity = appUpdateService.selectList(entityEntityWrapper);
        if (appUpdateEntity == null || appUpdateEntity.isEmpty()) {
            throw new FileNotFoundException("please upload app file first");
        }
        String fullPath = uploadPath + appUpdateEntity.get(0).getPath();
        DownloadUtil.download(request, response, fullPath);
    }
}
