package com.modules.generator.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.modules.generator.dao.DeviceDao;
import com.modules.generator.entity.DeviceEntity;
import com.modules.generator.pojo.DeviceSiteVO;
import com.modules.generator.service.DeviceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("deviceService")
public class DeviceServiceImpl extends ServiceImpl<DeviceDao, DeviceEntity> implements DeviceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DeviceEntity> page = this.selectPage(
                new Query<DeviceEntity>(params).getPage(),
                getEntityWrapper(params)
        );

        return new PageUtils(page);
    }

    @Override
    public List<DeviceEntity> selectByMn(String mn) {
        EntityWrapper<DeviceEntity> ew = new EntityWrapper();
        ew.eq("mn", mn);
        return this.selectList(ew);
    }

    @Override
    public List<DeviceEntity> selectBySiteId(Integer id) {
        EntityWrapper<DeviceEntity> ew = new EntityWrapper();
        ew.eq("site_id", id);
        return this.selectList(ew);
    }

    @Override
    public PageUtils listDeviceSite(Map<String, Object> params) {
        Page page = new Query<DeviceSiteVO>(params).getPage();
        List<DeviceSiteVO> res = this.baseMapper.listDeviceSiteByMap(page, getEntityWrapper(params));
        page.setRecords(res);
        return new PageUtils(page);
    }

    public static EntityWrapper getEntityWrapper(Map<String, Object> params) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if ("like".equals(params.get("searchType")) && params.get("key") != null
                && params.get("key").toString().trim() != "") {
            String key = params.get("key").toString();
            String value = params.get("value") == null ? "" : params.get("value").toString();
            entityWrapper.like(key, value);
        } else {
            if (params.get("mn") != null) {
                entityWrapper.eq("mn", params.get("mn"));
            } else if (params.get("id") != null) {
                entityWrapper.eq("id", params.get("id"));
            } else if (params.get("siteId") != null) {
                entityWrapper.eq("site_id", params.get("siteId"));
            }
        }
        return entityWrapper;
    }

    @Override
    public DeviceSiteVO selectByDeviceMn(String mn) {
        return this.baseMapper.selectByDeviceMn(mn);
    }


}
