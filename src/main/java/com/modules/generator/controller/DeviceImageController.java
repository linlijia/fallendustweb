package com.modules.generator.controller;

import com.common.exception.RRException;
import com.common.utils.DateUtils;
import com.common.utils.PageUtils;
import com.common.utils.R;
import com.modules.generator.pojo.DeviceSiteVO;
import com.modules.generator.service.DeviceImageService;
import com.modules.generator.entity.DeviceImageEntity;
import com.modules.generator.service.DeviceService;
import com.modules.generator.service.SiteService;
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
@RequestMapping("generator/deviceimage")
public class DeviceImageController {
    @Autowired
    private DeviceImageService deviceImageService;
    @Value("${upload-file-path}")
    private String uploadPath;
    @Autowired
    private DeviceService deviceService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:deviceimage:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = deviceImageService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:deviceimage:info")
    public R info(@PathVariable("id") Integer id) {
        DeviceImageEntity deviceImage = deviceImageService.selectById(id);

        return R.ok().put("deviceImage", deviceImage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:deviceimage:save")
    public R save(@RequestBody DeviceImageEntity deviceImage) {
        DeviceSiteVO deviceSiteVO = deviceService.selectByDeviceMn(deviceImage.getMn());
        if (deviceSiteVO == null) {
            return R.error(400, "设备不存在,请先添加设备");
        }
        deviceImage.setSiteId(deviceSiteVO.getSiteId());
        deviceImage.setSiteName(deviceSiteVO.getSiteName());
        deviceImageService.insert(deviceImage);

        return R.ok();
    }

    /**
     * 上传文件
     */
    @PostMapping("/upload/{mn}")
    public R upload(@RequestParam("file") MultipartFile file, @PathVariable("mn") String mn) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }

        String dateTime = DateUtils.format(new Date(), DateUtils.DATE_PATTERN);

        String path = uploadPath + mn + "/" + dateTime;
        File target = new File(path);
        if (!target.exists()) {
            target.mkdirs();
        }

        String filename = file.getOriginalFilename();
        File newFile = new File(path, filename);
        file.transferTo(newFile);
        DeviceImageEntity deviceImageEntity = new DeviceImageEntity();
        deviceImageEntity.setMn(mn);
        deviceImageEntity.setPath(filename);

        DeviceSiteVO deviceSiteVO = deviceService.selectByDeviceMn(deviceImageEntity.getMn());
        deviceImageEntity.setSiteId(deviceSiteVO.getSiteId());
        deviceImageEntity.setSiteName(deviceSiteVO.getSiteName());
        deviceImageService.insert(deviceImageEntity);

        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:deviceimage:update")
    public R update(@RequestBody DeviceImageEntity deviceImage) {
        deviceImageService.updateById(deviceImage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:deviceimage:delete")
    public R delete(@RequestBody Integer[] ids) {
        deviceImageService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
