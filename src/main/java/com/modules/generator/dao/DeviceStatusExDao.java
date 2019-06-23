package com.modules.generator.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.modules.generator.entity.DeviceStatus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeviceStatusExDao extends BaseMapper<DeviceStatus> {
	
}
