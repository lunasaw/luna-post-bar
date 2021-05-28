package com.luna.post.config;
import com.luna.post.entity.User;
import com.luna.post.utils.CookieUtils;
import com.luna.redis.util.RedisHashUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

;

/**
 * @author luna@mac
 * 2021年04月10日 10:37
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger log        = LoggerFactory.getLogger(LoginInterceptor.class);

    public static String        sessionKey = "luna:session:";

    private final RedisHashUtil redisHashUtil;

    public LoginInterceptor(RedisHashUtil redisHashUtil) {
        this.redisHashUtil = redisHashUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        String oneSessionKey = CookieUtils.getSessionKeyFromRequest(request);
        if (oneSessionKey == null) {
            // 若不满验证，则直接跳转到登录界面
            response.sendRedirect(request.getContextPath() + "/login.html");
            return false;
        }
        User user = (User)redisHashUtil.get(LoginInterceptor.sessionKey + ":" + oneSessionKey, oneSessionKey);
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login.html");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {

    }
}
