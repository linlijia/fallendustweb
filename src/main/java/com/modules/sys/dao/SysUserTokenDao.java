package com.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.modules.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserTokenEntity> {

    SysUserTokenEntity queryByToken(String token);

}
