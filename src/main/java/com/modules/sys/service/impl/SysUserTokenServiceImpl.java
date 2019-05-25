package com.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.R;
import com.modules.sys.entity.SysUserTokenEntity;
import com.modules.sys.dao.SysUserTokenDao;
import com.modules.sys.entity.SysUserEntity;
import com.modules.sys.oauth2.TokenGenerator;
import com.modules.sys.service.SysUserService;
import com.modules.sys.service.SysUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service("sysUserTokenService")
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenDao, SysUserTokenEntity> implements SysUserTokenService {
	//12小时后过期
	private final static int EXPIRE = 3600 * 12;
	@Autowired
    private SysUserService sysUserService;

	@Override
	public R createToken(long userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();

		//当前时间
		Date now = new Date();
		//过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		//判断是否生成过token
		SysUserTokenEntity tokenEntity = this.selectById(userId);
		if(tokenEntity == null){
			tokenEntity = new SysUserTokenEntity();
			tokenEntity.setUserId(userId);
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//保存token
			this.insert(tokenEntity);
		}else{
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//更新token
			this.updateById(tokenEntity);
		}

		R r = R.ok().put("token", token).put("expire", EXPIRE);

		return r;
	}

	@Override
	public void logout(long userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();

		//修改token
		SysUserTokenEntity tokenEntity = new SysUserTokenEntity();
		tokenEntity.setUserId(userId);
		tokenEntity.setToken(token);
		this.updateById(tokenEntity);
	}

	@Override
	public SysUserEntity getCurrentUser(String token){
		EntityWrapper<SysUserTokenEntity> tokenEntity =  new EntityWrapper() ;
		tokenEntity.eq("token",token);
		SysUserTokenEntity sysUserTokenEntity =  this.selectOne(tokenEntity);
		if(sysUserTokenEntity!=null){
			Long userId = sysUserTokenEntity.getUserId();
			return sysUserService.selectById(userId);
		}
		return null;
	}

}
