package com.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.generator.entity.TroubleEntity;
import com.modules.generator.pojo.TroubleRankingVO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author novacaine
 * @email superman@gmail.com
 * @date 2019-06-23 23:54:50
 */
public interface TroubleService extends IService<TroubleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryTroubleVOPage(Map<String, Object> params);

    List<TroubleRankingVO> troubleRanking(Date start, Date end);
}

