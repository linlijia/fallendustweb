package com.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.common.utils.DateUtils;
import com.common.utils.R;
import com.modules.sys.entity.SysUserTokenEntity;
import com.modules.sys.entity.SysUserEntity;
import com.modules.sys.form.SysLoginForm;
import com.modules.sys.service.SysCaptchaService;
import com.modules.sys.service.SysUserService;
import com.modules.sys.service.SysUserTokenService;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Map;


@RestController
public class SysLoginController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;
    @Autowired
    private SysCaptchaService sysCaptchaService;

    /**
     * 验证码
     */
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //获取图片验证码
        BufferedImage image = sysCaptchaService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 登录
     */
    @PostMapping("/sys/login")
    public Map<String, Object> login(@RequestBody SysLoginForm form) throws IOException {
        boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
        if (!captcha) {
            return R.error("验证码不正确");
        }

        //用户信息
        SysUserEntity user = sysUserService.queryByUserName(form.getUsername());

        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
            return R.error("账号或密码不正确");
        }

        //账号锁定
        if (user.getStatus() == 0) {
            return R.error("账号已被锁定,请联系管理员");
        }

        //生成token，并保存到数据库
        R r = sysUserTokenService.createToken(user.getUserId());
        return r;
    }


    /**
     * 获取token
     */
    @PostMapping("/sys/token")
    public Map<String, Object> token(@RequestBody SysLoginForm form) throws IOException {
        //用户信息
        SysUserEntity user = sysUserService.queryByUserName(form.getUsername());

        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
            return R.error("账号或密码不正确");
        }

        //账号锁定
        if (user.getStatus() == 0) {
            return R.error("账号已被锁定,请联系管理员");
        }

        EntityWrapper<SysUserTokenEntity> wrapper = new EntityWrapper();
        wrapper.eq("user_id", user.getUserId());
        SysUserTokenEntity sysUserTokenEntity = sysUserTokenService.selectOne(wrapper);
        //存在未过期时间小于10分钟的token则返回此token
        if (sysUserTokenEntity != null && sysUserTokenEntity.getExpireTime().after(
                DateUtils.addDateMinutes(new Date(), 10))) {
            return R.ok().put("token", sysUserTokenEntity.getToken()).put("expire", sysUserTokenEntity.getExpireTime());
        }

        //生成token，并保存到数据库
        R r = sysUserTokenService.createToken(user.getUserId());
//        int expire = Integer.parseInt(r.get("expire").toString());
//        Date date = new Date();
//        r.put("expire", DateUtils.addDateSeconds(date, expire));
        return r;
    }


    /**
     * 退出
     */
    @PostMapping("/sys/logout")
    public R logout() {
        sysUserTokenService.logout(getUserId());
        return R.ok();
    }

}
