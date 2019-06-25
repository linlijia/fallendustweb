package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.DateUtils;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.modules.generator.dao.TroubleDao;
import com.modules.generator.entity.TroubleEntity;
import com.modules.generator.pojo.TroubleRankingVO;
import com.modules.generator.pojo.TroubleVO;
import com.modules.generator.service.TroubleService;
import org.springframework.stereotype.Service;

import java.util.*;


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

    @Override
    public List<TroubleRankingVO> troubleRanking(Date start, Date end) {
        List<TroubleVO> troubleVOS = this.baseMapper.troubleRanking(start, end);

        Map<String, Map<String, Set<String>>> ranking = new HashMap<>();
        for (TroubleVO troubleVO : troubleVOS) {
            String date = DateUtils.format(troubleVO.getHappenTime(), DateUtils.DATE_PATTERN);

            if (ranking.get(troubleVO.getSiteName()) == null) {
                Map<String, Set<String>> m = new HashMap<>();
                HashSet<String> dateSetOffline = new HashSet<>();
                m.put("offline", dateSetOffline);
                HashSet<String> dateSetTrouble = new HashSet<>();
                m.put("breakdown", dateSetTrouble);
                ranking.put(troubleVO.getSiteName(), m);
            }

            if (troubleVO.getTroubleCode() == null) {
                continue;
            }

            if (troubleVO.getTroubleCode() != 90101) {
                ranking.get(troubleVO.getSiteName()).get("offline").add(date);
            } else {
                ranking.get(troubleVO.getSiteName()).get("breakdown").add(date);
            }

        }

        List<TroubleRankingVO> result = new ArrayList<>();
        for (String siteName : ranking.keySet()) {
            TroubleRankingVO troubleRankingVO = new TroubleRankingVO();
            troubleRankingVO.setBreakdown(ranking.get(siteName).get("breakdown").size());
            troubleRankingVO.setOffline(ranking.get(siteName).get("offline").size());
            troubleRankingVO.setSiteName(siteName);
            troubleRankingVO.setTotal(troubleRankingVO.getBreakdown() + troubleRankingVO.getOffline());
            result.add(troubleRankingVO);
        }
        Collections.sort(result, (a, b) -> b.getTotal().compareTo(a.getTotal()));
        return result;
    }
}