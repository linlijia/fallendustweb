package com.modules.generator.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.modules.generator.dao.MouthReportDao;
import com.modules.generator.entity.MouthReportEntity;
import com.modules.generator.service.MouthReportService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("mouthReportService")
public class MouthReportServiceImpl extends ServiceImpl<MouthReportDao, MouthReportEntity> implements MouthReportService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MouthReportEntity> page = this.selectPage(
                new Query<MouthReportEntity>(params).getPage(),
                new EntityWrapper<>()
        );

        return new PageUtils(page);
    }

}