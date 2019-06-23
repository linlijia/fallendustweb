package com.modules.generator.controller;

import com.common.utils.R;
import com.modules.generator.entity.TroubleEntity;
import com.modules.generator.service.TroubleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generator/trouble")
public class TroubleController {

    @Autowired
    private TroubleService troubleService;

    @PostMapping("/save")
    public R saveTrouble(@RequestBody TroubleEntity troubleEntity) {

        boolean result = troubleService.insert(troubleEntity);
        return result ? R.ok() : R.error();
    }


}
