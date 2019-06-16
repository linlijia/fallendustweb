package com.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.PageUtils;
import com.modules.sys.entity.SysRoleEntity;

import java.util.List;
import java.util.Map;

public interface SysRoleService extends IService<SysRoleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(SysRoleEntity role);

    void update(SysRoleEntity role);

    void deleteBatch(Long[] roleIds);


    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);
}
