package com.modules.generator.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.common.utils.PageUtils;
import com.common.utils.R;
import com.common.utils.ShiroUtils;
import com.modules.generator.entity.OpsImageEntity;
import com.modules.generator.entity.OpsRecordEntity;
import com.modules.generator.entity.OpsTaskEntity;
import com.modules.generator.entity.SiteEntity;
import com.modules.generator.service.OpsImageService;
import com.modules.generator.service.OpsRecordService;
import com.modules.generator.service.OpsTaskService;
import com.modules.generator.service.SiteService;
import com.modules.sys.entity.SysUserEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("generator/opsrecord")
public class OpsRecordController {
    @Autowired
    private OpsRecordService opsRecordService;
    @Autowired
    private OpsTaskService opsTaskService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private OpsImageService opsImageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:opsrecord:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = opsRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:opsrecord:info")
    public R info(@PathVariable("id") Integer id) {
        OpsRecordEntity opsRecord = opsRecordService.selectById(id);
        opsRecord.setOpsImage(opsImageService.selectList(new EntityWrapper<OpsImageEntity>().eq("record_id", opsRecord.getId())));

        return R.ok().put("opsRecord", opsRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:opsrecord:save")
    public R save(@RequestBody OpsRecordEntity opsRecord) {
        opsRecordService.insert(opsRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:opsrecord:update")
    public R update(@RequestBody OpsRecordEntity opsRecord) {
        if (opsRecord.getId() == null || opsRecord.getId() == 0) {
            if (opsRecord.getTaskId() != null && opsRecord.getTaskId() > 0) {
                OpsTaskEntity opsTaskEntity = opsTaskService.selectById(opsRecord.getTaskId());
                if (opsTaskEntity != null) {
                    opsRecord.setSiteId(opsTaskEntity.getSiteId());
                    opsRecord.setSiteName(opsTaskEntity.getSiteName());
                    SiteEntity siteEntity = siteService.selectById(opsTaskEntity.getSiteId());
                    opsRecord.setSiteContact(siteEntity.getContact());
                    opsRecord.setAddress(siteEntity.getAddress());
                    opsRecord.setSitePhoneNum(siteEntity.getPhoneNo());
                    SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
                    opsRecord.setOpsUser(sysUserEntity.getUserId().intValue());
                    opsRecord.setOpsUserName(sysUserEntity.getName());
                    opsRecord.setTaskType(opsTaskEntity.getTaskType());
                }
            }
            opsRecordService.insert(opsRecord);
        } else {
            opsRecordService.updateById(opsRecord);
        }
        List<OpsImageEntity> opsImageEntityList = opsRecord.getOpsImage();

        if (opsImageEntityList != null && opsImageEntityList.size() > 0) {
            for (OpsImageEntity opsImageEntity : opsImageEntityList) {
                opsImageEntity.setRecordId(opsRecord.getId());
                opsImageService.updateById(opsImageEntity);
            }
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:opsrecord:delete")
    public R delete(@RequestBody Integer[] ids) {
        opsRecordService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 根据task id 查询
     */


    /**
     * *@RequiresPermissions("generator:opsrecord:byTaskId")
     */
    @RequestMapping("/byTaskId/{taskId}")
    public R byTaskId(@PathVariable("taskId") Integer taskId) {
        EntityWrapper<OpsRecordEntity> entityEntityWrapper = new EntityWrapper<>();
        entityEntityWrapper.eq("task_id", taskId);
        List<OpsRecordEntity> opsRecord = opsRecordService.selectList(entityEntityWrapper);
        if (opsRecord == null || opsRecord.size() == 0) {
            return R.ok().put("opsRecord", null);
        }
        for (OpsRecordEntity opsRecordEntity : opsRecord) {
            opsRecordEntity.setOpsImage(opsImageService.selectList(new EntityWrapper<OpsImageEntity>().eq("record_id", opsRecordEntity.getId())));
        }

        return R.ok().put("opsRecord", opsRecord);
    }

}
