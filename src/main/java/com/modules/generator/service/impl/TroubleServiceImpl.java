package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.modules.generator.dao.TroubleDao;
import com.modules.generator.entity.TroubleEntity;
import com.modules.generator.service.TroubleService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("troubleService")
public class TroubleServiceImpl extends ServiceImpl<TroubleDao, TroubleEntity> implements TroubleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        return null;
    }
}
