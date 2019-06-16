package com.modules.generator.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.utils.Query;
import com.common.utils.SpringContextUtils;
import com.modules.generator.pojo.DeviceSiteVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DeviceDaoTest {
    @Test
    public void listDeviceSiteByMap() {
        DeviceDao deviceDao = (DeviceDao) SpringContextUtils.getBean("deviceDao");
        Map<String, Object> params = new HashMap<>();
        params.put("page", "1");
        params.put("limit", "10");
        Page page = new Query<DeviceSiteVO>(params).getPage();

        List<DeviceSiteVO> res = deviceDao.listDeviceSiteByMap(page, new EntityWrapper<DeviceSiteVO>().like("site", ""));
        for (DeviceSiteVO r : res) {
            System.out.println(r.getSite());
        }
    }

}