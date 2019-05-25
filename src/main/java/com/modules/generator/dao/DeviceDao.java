package com.modules.generator.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.modules.generator.entity.DeviceEntity;
import com.modules.generator.pojo.DeviceSiteVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeviceDao extends BaseMapper<DeviceEntity> {
    @Select("select * from dust_device inner join dust_site on dust_device.site_id=dust_site.id ${ew.sqlSegment}")
    List<DeviceSiteVO> listDeviceSiteByMap(Page<DeviceSiteVO> param, @Param("ew") Wrapper<DeviceSiteVO> wrapper);

    @Select("select * from dust_device inner join dust_site on dust_device.site_id=dust_site.id  where dust_device.mn = '${mn}'")
    DeviceSiteVO selectByDeviceMn(@Param("mn") String mn);
}