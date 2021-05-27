package com.luna.post.user;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.encrypt.HashTools;
import com.luna.common.text.ObjectUtils;
import com.luna.common.text.RandomStrUtil;
import com.luna.post.config.LoginInterceptor;
import com.luna.post.entity.Register;
import com.luna.post.entity.User;
import com.luna.post.entity.UserException;
import com.luna.post.req.RegisterReq;
import com.luna.post.service.RegisterService;
import com.luna.post.service.UserService;
import com.luna.redis.util.RedisHashUtil;
import com.luna.redis.util.RedisKeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author luna@mac
 * 2021年04月29日 10:02
 */
@Service
public class LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private RedisHashUtil redisHashUtil;

    @Autowired
    private RedisKeyUtil redisKeyUtil;

    public static final int SESSION_TIME = 24 * 60 * 60;

    /**
     * session过期时间，单位天
     */
    public static final int SESSION_EXPIRED = 7;

    public String login(String username, String password) {
        User user = userService.getByEntity(new User(username));
        if (user == null) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "用户不存在");
        }

        String s = HashTools.md5(HashTools.md5(password));
        if (!StringUtils.equals(user.getPassword(), s)) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "密码错误");
        }
        user.setLoginTime(new Date());
        userService.update(user);
        String nonceStrWithUUID = RandomStrUtil.generateNonceStrWithUUID();
        redisHashUtil.set(LoginInterceptor.sessionKey + ":" + nonceStrWithUUID,
                ImmutableMap.of(nonceStrWithUUID, user));
        redisKeyUtil.expire(LoginInterceptor.sessionKey + ":" + nonceStrWithUUID, SESSION_TIME * SESSION_EXPIRED,
                null);
        return nonceStrWithUUID;
    }

    public static void main(String[] args) {
        System.out.println(HashTools.md5(HashTools.md5("admin123")));
    }

    public User sysUser(String sessionKey) {
        return (User) redisHashUtil.get(LoginInterceptor.sessionKey + ":" + sessionKey, sessionKey);
    }

    public Boolean editPassword(String sessionKey, String oldPassword, String newPassword) {
        User user = (User) redisHashUtil.get(LoginInterceptor.sessionKey + ":" + sessionKey, sessionKey);
        User byId = userService.getById(user.getId());

        if (!byId.getPassword().equals(HashTools.md5(HashTools.md5(oldPassword)))) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "密码错误");
        }

        byId.setPassword(HashTools.md5(HashTools.md5(newPassword)));
        return userService.update(byId) == 1;
    }

    public Boolean logout(String oneSessionKey) {
        return redisKeyUtil.delete(Collections.singleton(LoginInterceptor.sessionKey + ":" + oneSessionKey)) == 1;
    }

    public boolean register(RegisterReq registerReq) {
        Register register = new Register();
        User user = new User();
        user.setName(registerReq.getUsername());
        if (userService.countByEntity(user) == 1) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "用户已经存在");
        }
        if (!ObjectUtils.nullSafeEquals(registerReq.getPassword(), registerReq.getEnsurePassword())) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "密码错误");
        }
        user.setPassword(HashTools.md5(HashTools.md5(registerReq.getPassword())));
        if (userService.insert(user) == 1) {
            register.setUserId(user.getId());
            register.setSex(registerReq.getSex());
            register.setAge(registerReq.getAge());
            register.setEmail(registerReq.getEmail());
            return registerService.insert(register) == 1;
        }
        return false;
    }
}
