package com.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.common.utils.R;
import com.modules.sys.entity.SysUserTokenEntity;
import com.modules.sys.entity.SysUserEntity;

public interface SysUserTokenService extends IService<SysUserTokenEntity> {

    /**
     * 生成token
     *
     * @param userId 用户ID
     */
    R createToken(long userId);

    /**
     * 退出，修改token值
     *
     * @param userId 用户ID
     */
    void logout(long userId);

    SysUserEntity getCurrentUser(String token);
}
