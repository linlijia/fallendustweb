package com.modules.generator.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.common.utils.R;
import com.modules.generator.entity.FrontendAppStatusEntity;
import com.modules.generator.service.FrontendAppStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/generator/frontendappstatus")
public class FrontendAppStatusController {
    @Autowired
    public FrontendAppStatusService frontendAppStatusService;

    @PostMapping("/save")
    public R saveOrUpdate(@RequestBody FrontendAppStatusEntity frontendAppStatusEntity) {
        FrontendAppStatusEntity row = frontendAppStatusService.selectOne(new EntityWrapper<FrontendAppStatusEntity>().eq("mn", frontendAppStatusEntity.getMn()));
        if (row != null) {
            frontendAppStatusEntity.setId(row.getId());
        }
        boolean result = frontendAppStatusService.insertOrUpdate(frontendAppStatusEntity);
        return result ? R.ok() : R.error();
    }
}
