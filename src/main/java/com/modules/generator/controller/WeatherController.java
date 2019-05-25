package com.modules.generator.controller;

import com.common.utils.R;
import com.common.utils.WeatherUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("generator/weather")
public class WeatherController {
    @RequestMapping("/{cityId}")
    public R list(@PathVariable("cityId") String cityId) throws Exception {
        return R.ok().put("data", WeatherUtils.getweatherbycity(cityId));
    }
}
