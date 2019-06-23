package com.modules.generator.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.modules.generator.entity.DeviceStatusEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Mapper
public interface DeviceStatusDao extends BaseMapper<DeviceStatusEntity> {
    @Select("select count(*) from dust_device left join dust_device_status on dust_device.mn" +
            " = dust_device_status.mn where data_time > #{data_time,jdbcType=TIMESTAMP}")
    int selectOnlineCount(@Param("data_time") Date dataTime);

}
