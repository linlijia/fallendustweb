

package com.modules.job.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.job.entity.ScheduleJobLogEntity;

import java.util.Map;

public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
