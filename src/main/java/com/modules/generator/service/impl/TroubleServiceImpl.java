package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.modules.generator.dao.TroubleDao;
import com.modules.generator.entity.TroubleEntity;
import com.modules.generator.pojo.TroubleVO;
import com.modules.generator.service.TroubleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("troubleService")
public class TroubleServiceImpl extends ServiceImpl<TroubleDao, TroubleEntity> implements TroubleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TroubleEntity> page = this.selectPage(
                new Query<TroubleEntity>(params).getPage(),
                new EntityWrapper<TroubleEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryTroubleVOPage(Map<String, Object> params) {
        EntityWrapper<TroubleVO> ew = new EntityWrapper();
        if ("like".equals(params.get("searchType")) && params.get("key") != null
                && params.get("key").toString().trim() != "") {
            String key = params.get("key").toString();
            String value = params.get("value") == null ? "" : params.get("value").toString();
            ew.like(key, value);
        }

        Page page = new Query<TroubleEntity>(params).getPage();
        List<TroubleVO> troubleVOList = this.baseMapper.voPage(page, ew);
        page.setRecords(troubleVOList);
        return new PageUtils(page);
    }
}