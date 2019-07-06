package com.modules.generator.dao;

import com.common.utils.SpringContextUtils;
import com.modules.generator.entity.DeviceDataEntity;
import com.modules.generator.service.DeviceDataService;
import com.modules.job.task.MonthlyDustFallTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author : linli
 * @time : 2019/7/5 14:28
 * @description :
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class MonthlyDustFallTest {

    @Test
    public void calculateMonthlyDustFall() {
        MonthlyDustFallTask monthlyDustFallTask = (MonthlyDustFallTask) SpringContextUtils.getBean("monthlyDustFallTask");
        monthlyDustFallTask.calculateMonthlyDustFall();
    }
}
