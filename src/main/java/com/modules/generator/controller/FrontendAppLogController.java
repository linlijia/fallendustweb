package com.modules.generator.controller;

import com.common.utils.R;
import com.modules.generator.entity.FrontendLogEntity;
import com.modules.generator.service.FrontendAppLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generator/frontendapplog")
public class FrontendAppLogController  {

    @Autowired
    private FrontendAppLogService frontendAppLogService;

    @PostMapping("/save")
    public R save(@RequestBody FrontendLogEntity frontendLogEntity) {
        boolean result = frontendAppLogService.insert(frontendLogEntity);
        return result ? R.ok() : R.error();
    }



}
