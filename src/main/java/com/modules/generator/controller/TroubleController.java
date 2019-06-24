package com.modules.generator.controller;

import com.common.utils.PageUtils;
import com.common.utils.R;
import com.modules.generator.Enums;
import com.modules.generator.entity.TroubleEntity;
import com.modules.generator.service.TroubleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * @author novacaine
 * @email superman@gmail.com
 * @date 2019-06-23 23:54:50
 */
@RestController
@RequestMapping("generator/trouble")
public class TroubleController {
    @Autowired
    private TroubleService troubleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:trouble:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = troubleService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:trouble:info")
    public R info(@PathVariable("id") Integer id) {
        TroubleEntity trouble = troubleService.selectById(id);

        return R.ok().put("trouble", trouble);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:trouble:save")
    public R save(@RequestBody TroubleEntity trouble) {
        troubleService.insert(trouble);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:trouble:update")
    public R update(@RequestBody TroubleEntity trouble) {
        troubleService.updateById(trouble);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:trouble:delete")
    public R delete(@RequestBody Integer[] ids) {
        troubleService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 异常状态列表
     */
    @RequestMapping("/type")
//    @RequiresPermissions("generator:trouble:type")
    public R type() {
        List<Map<String, String>> types = new ArrayList<>(28);
        for (Enums.TroubleType troubleType : Enums.TroubleType.values()) {
            Map type = new HashMap<>();
            type.put("value", troubleType.getCode());
            type.put("label", troubleType.getTroubleName());
            types.add(type);
        }
        return R.ok().put("data", types);
    }
}
