package com.modules.generator.controller;

import com.common.utils.PageUtils;
import com.common.utils.R;
import com.common.utils.ShiroUtils;
import com.modules.generator.entity.NotifyEntity;
import com.modules.generator.service.NotifyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


@RestController
@RequestMapping("generator/notify")
public class NotifyController {
    @Autowired
    private NotifyService notifyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:notify:list")
    public R list(@RequestParam Map<String, Object> params) {
        int userId = ShiroUtils.getUserId().intValue();
        System.out.println(userId);
        if (userId != 1) {
            params.put("user_id", userId);
        }
        PageUtils page = notifyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:notify:info")
    public R info(@PathVariable("id") Integer id) {
        NotifyEntity notify = notifyService.selectById(id);

        return R.ok().put("notify", notify);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:notify:save")
    public R save(@RequestBody NotifyEntity notify) {
        notifyService.insert(notify);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:notify:update")
    public R update(@RequestBody NotifyEntity notify) {
        notifyService.updateById(notify);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:notify:delete")
    public R delete(@RequestBody Integer[] ids) {
        notifyService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
