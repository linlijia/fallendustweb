package com.modules.generator.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.modules.generator.entity.DeviceDataEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface DeviceDataDao extends BaseMapper<DeviceDataEntity> {
    @Select("SELECT * FROM dust_device_data WHERE mn = '${mn}' data_time BETWEEN '${startTime}' AND '${endTime}' ")
    List<DeviceDataEntity> listDeviceDataByTimes(@Param("mn") String mn,  @Param("startTime") String startTime, @Param("endTime") String endTime);

    @Select("SELECT * FROM dust_device_data WHERE mn = '${mn}' AND data_time BETWEEN #{startTime, jdbcType=TIMESTAMP} AND #{endTime, jdbcType=TIMESTAMP} " +
            "AND a34011_flag = 'N' ORDER BY data_time DESC LIMIT 0, 1 ")
    DeviceDataEntity selectLastMonthLastData(@Param("mn") String mn, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
