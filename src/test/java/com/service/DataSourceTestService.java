package com.service;

import com.datasources.DataSourceNames;
import com.datasources.annotation.DataSource;
import com.datasources.DataSourceNames;
import com.datasources.annotation.DataSource;
import com.modules.sys.entity.SysUserEntity;
import com.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataSourceTestService {
    @Autowired
    private SysUserService sysUserService;

    public SysUserEntity queryUser(Long userId){
        return sysUserService.selectById(userId);
    }

    @DataSource(name = DataSourceNames.SECOND)
    public SysUserEntity queryUser2(Long userId){
        return sysUserService.selectById(userId);
    }
}
