package com.modules.generator.controller;

import com.common.exception.RRException;
import com.common.utils.DateUtils;
import com.common.utils.PageUtils;
import com.common.utils.R;
import com.common.utils.ShiroUtils;
import com.modules.generator.entity.OpsImageEntity;
import com.modules.generator.service.OpsImageService;
import com.modules.sys.entity.SysUserEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("generator/opsimage")
public class OpsImageController {
    @Autowired
    private OpsImageService opsImageService;
    @Value("${upload-file-path}")
    private String uploadPath;
    @Value("${file-server}")
    private String fileServer;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:opsimage:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = opsImageService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:opsimage:info")
    public R info(@PathVariable("id") Integer id) {
        OpsImageEntity opsImage = opsImageService.selectById(id);

        return R.ok().put("opsImage", opsImage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:opsimage:save")
    public R save(@RequestBody OpsImageEntity opsImage) {
        opsImageService.insert(opsImage);

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

        String path = uploadPath + "opsImage/" + dateTime + "/";
        File target = new File(path);
        if (!target.exists()) {
            target.mkdirs();
        }

        String filename = file.getOriginalFilename();
        File newFile = new File(path, filename);
        file.transferTo(newFile);
        OpsImageEntity opsImageEntity = new OpsImageEntity();
        String filePath = fileServer + "/files/" + "opsImage/" + dateTime + "/" + filename;
        opsImageEntity.setFilePath(filePath);
        SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
        opsImageEntity.setUploadUser(sysUserEntity.getUserId().intValue());
        opsImageEntity.setUploadUserName(sysUserEntity.getName());
        opsImageService.insert(opsImageEntity);
        return R.ok().put("data", opsImageEntity);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:opsimage:update")
    public R update(@RequestBody OpsImageEntity opsImage) {
        opsImageService.updateById(opsImage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:opsimage:delete")
    public R delete(@RequestBody Integer[] ids) {
        opsImageService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
