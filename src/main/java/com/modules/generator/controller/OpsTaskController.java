package com.modules.generator.controller;

import com.common.annotation.SysLog;
import com.common.utils.PageUtils;
import com.common.utils.R;
import com.common.utils.ShiroUtils;
import com.modules.generator.Enums;
import com.modules.generator.entity.NotifyEntity;
import com.modules.generator.entity.OpsTaskEntity;
import com.modules.generator.entity.SiteEntity;
import com.modules.generator.pojo.OpsTasktVO;
import com.modules.generator.service.OpsTaskService;
import com.modules.generator.service.SiteService;
import com.modules.generator.service.impl.NotifyServiceImpl;
import com.modules.sys.entity.SysUserEntity;
import com.modules.sys.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("generator/opstask")
public class OpsTaskController {
    @Autowired
    private OpsTaskService opsTaskService;

    @Autowired
    private NotifyServiceImpl notifyService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SiteService siteService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:opstask:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = opsTaskService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:opstask:info")
    public R info(@PathVariable("id") Integer id) {
        OpsTaskEntity opsTask = opsTaskService.selectById(id);
        OpsTasktVO opsTasktVO = new OpsTasktVO();
        String[] userIDs = opsTask.getOpsUser().split(",");

        opsTasktVO.setOpsTask(opsTask);
        opsTasktVO.setOpsUsers(sysUserService.selectBatchIds(Arrays.asList(userIDs)));
        opsTasktVO.setSites(siteService.selectBatchIds(Arrays.asList(opsTask.getSiteId())));
        return R.ok().put("opsTask", opsTasktVO);
    }

    /**
     * 保存
     */
    @SysLog("新增运维任务")
    @RequestMapping("/save")
    @RequiresPermissions("generator:opstask:save")
    public R save(@RequestBody OpsTasktVO opsTaskVO) {
        InsertOrUpdate(opsTaskVO);
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改运维任务")
    @RequestMapping("/update")
    @RequiresPermissions("generator:opstask:update")
    public R update(@RequestBody OpsTasktVO opsTaskVO) {
        InsertOrUpdate(opsTaskVO);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除运维任务")
    @RequestMapping("/delete")
    @RequiresPermissions("generator:opstask:delete")
    public R delete(@RequestBody Integer[] ids) {
        opsTaskService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    public void InsertOrUpdate(OpsTasktVO opsTaskVO) {
        List<SiteEntity> siteEntityList = opsTaskVO.getSites();
        List<SysUserEntity> opsUserEntities = opsTaskVO.getOpsUsers();
        List<String> userIds = new ArrayList<>();
        List<String> usernameList = new ArrayList<>();
        List<String> siteList = new ArrayList<>();
        for (SysUserEntity userEntity : opsUserEntities) {
            userIds.add(userEntity.getUserId().toString());
            usernameList.add(userEntity.getName());
        }
        String usernames = String.join(",", usernameList);
        SysUserEntity userEntity = ShiroUtils.getUserEntity();
        OpsTaskEntity opsTask = opsTaskVO.getOpsTask();
        Integer taskId = opsTask.getId();
        for (SiteEntity siteEntity : siteEntityList) {
            opsTask.setId(taskId);
            opsTask.setSiteId(siteEntity.getId());
            opsTask.setSiteName(siteEntity.getSite());
            siteList.add(siteEntity.getSite());
            opsTask.setOpsUser(String.join(",", userIds));
            opsTask.setOpsUserName(usernames);
            opsTask.setAssigner(userEntity.getUserId().intValue());
            opsTask.setAssignerName(userEntity.getName());
            opsTask.setOpsStatus("待执行");
            opsTaskService.insertOrUpdate(opsTask);
        }

        String siteNames = String.join(",", siteList);

        for (SysUserEntity sysUserEntity : opsUserEntities) {
            NotifyEntity notifyEntity = new NotifyEntity();
            notifyEntity.setContent(String.format("%s: 你好，明天你有任务需要执行，分别是：%s。\n 任务执行人；%s \n",
                    sysUserEntity.getName(), siteNames, usernames)
            );
            notifyEntity.setCreator(userEntity.getUserId().intValue());
            notifyEntity.setCreatorName(userEntity.getName());
            notifyEntity.setUserName(sysUserEntity.getName());
            notifyEntity.setUserId(sysUserEntity.getUserId().intValue());
            notifyEntity.setType(Enums.NotifyMsgType.OpsTask.getMsgType());
            notifyService.insert(notifyEntity);
        }
    }


    /**
     * 签到
     */
    @SysLog("运维任务签到")
    @RequestMapping("/checkIn")
//    @RequiresPermissions("generator:opstask:checkIn")
    public R checkIn(@RequestBody OpsTaskEntity opsTask) {
        OpsTaskEntity opsTaskEntity = opsTaskService.selectById(opsTask.getId());
        SysUserEntity userEntity = ShiroUtils.getUserEntity();
        String opsUsers = opsTaskEntity.getOpsUser();
        if (opsUsers != null && !opsUsers.trim().equals("")) {
            String[] userIds = opsUsers.split(",");
            if (Arrays.asList(userIds).contains(userEntity.getUserId().toString())) {
                if (opsTaskEntity.getCheckIn() == null) {
                    opsTask.setCheckIn(new Date());
                    opsTask.setOpsStatus("正在执行");
                    opsTaskService.updateById(opsTask);
                }
                return R.ok();
            }
        }
        return R.error(403, "您不是本次运维执行人，没有签到权限");
    }
}
