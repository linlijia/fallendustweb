package com.modules.generator.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.modules.generator.entity.SiteEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SiteDao extends BaseMapper<SiteEntity> {
	
}
