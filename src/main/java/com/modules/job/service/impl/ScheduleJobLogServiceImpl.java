

package com.modules.job.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.modules.job.dao.ScheduleJobLogDao;
import com.modules.job.service.ScheduleJobLogService;
import com.modules.job.entity.ScheduleJobLogEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogDao, ScheduleJobLogEntity> implements ScheduleJobLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String jobId = (String) params.get("jobId");

        Page<ScheduleJobLogEntity> page = this.selectPage(
                new Query<ScheduleJobLogEntity>(params).getPage(),
                new EntityWrapper<ScheduleJobLogEntity>().like(StringUtils.isNotBlank(jobId), "job_id", jobId)
        );

        return new PageUtils(page);
    }

}
