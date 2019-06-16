package com.common.utils;

import com.alibaba.fastjson.JSONObject;

public class WeatherUtils {
    public static JSONObject getweatherbycity(String cityCode) throws Exception {
        return HttpUtils.doGet("http://t.weather.sojson.com/api/weather/city/" + cityCode);
    }
}
