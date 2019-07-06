package com.modules.generator.controller;

import com.common.utils.PageUtils;
import com.common.utils.R;
import com.modules.generator.entity.MonthlyDustFall;
import com.modules.generator.service.MonthlyDustFallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @author : linli
 * @time : 2019/7/5 14:06
 * @description :
 */
@RestController
@RequestMapping("/generator/monthlydustfall")
public class MonthlyDustFallController {

    @Autowired
    private MonthlyDustFallService monthlyDustFallService;

    @PostMapping("/")
    public R postMonthlyDustFall(@RequestBody MonthlyDustFall monthlyDustFall) {
        return monthlyDustFallService.insert(monthlyDustFall) ? R.ok() : R.error();
    }

    @GetMapping("/list")
    public R searctMonthlyDustFall(@RequestParam Map<String, Object> params) {
        PageUtils page = monthlyDustFallService.queryPage(params);
        List<MonthlyDustFall> monthlyDustFallList = (List<MonthlyDustFall>) page.getList();
        page.setList(monthlyDustFallList);
        return R.ok().put("page", page);
    }

    @PostMapping("/list")
    public R postMonthlyDustFallList(@RequestBody List<MonthlyDustFall> monthlyDustFallList) {
        return monthlyDustFallService.insertBatch(monthlyDustFallList) ? R.ok() : R.error();
    }
}
