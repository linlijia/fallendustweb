package com.modules.sys.service;

import com.modules.sys.entity.SysUserTokenEntity;
import com.modules.sys.entity.SysUserEntity;

import java.util.Set;

public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    SysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     *
     * @param userId
     */
    SysUserEntity queryUser(Long userId);
}
