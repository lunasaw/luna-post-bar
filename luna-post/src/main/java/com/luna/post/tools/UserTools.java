package com.luna.post.tools;

import com.luna.common.dto.constant.ResultCode;
import com.luna.post.config.LoginInterceptor;
import com.luna.post.entity.User;
import com.luna.post.entity.UserException;
import com.luna.redis.util.RedisHashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luna
 * 2021/5/31
 */
@Service
public class UserTools {

    @Autowired
    private RedisHashUtil redisHashUtil;

    public User getUser(String sessionKey) {
        if (sessionKey == null) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "用户不存在");
        }

        return (User)redisHashUtil.get(LoginInterceptor.sessionKey + ":" + sessionKey, sessionKey);
    }
}
