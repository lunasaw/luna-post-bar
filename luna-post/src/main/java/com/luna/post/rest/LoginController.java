package com.luna.post.rest;

import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.constant.ResultCode;
import com.luna.post.entity.User;
import com.luna.post.req.EditPasswordReq;
import com.luna.post.req.LoginReq;
import com.luna.post.req.RegisterReq;
import com.luna.post.user.LoginService;
import com.luna.post.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author luna@mac
 * 2021年04月29日 09:59
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("/register")
    public ResultDTO<Boolean> register(@RequestBody RegisterReq registerReq) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, loginService.register(registerReq));
    }

    @PostMapping("/login")
    public ResultDTO<String> login(@RequestBody LoginReq loginReq, HttpServletResponse response) {
        String sessionKey = loginService.login(loginReq.getUsername(), loginReq.getPassword());
        Cookie cookie = new Cookie(CookieUtils.SESSION_KEY_NAME, sessionKey);
        cookie.setPath("/");
        if (Objects.equals(loginReq.getRememberPwd(), "on")) {
            cookie.setMaxAge(LoginService.SESSION_TIME * LoginService.SESSION_EXPIRED);
        } else {
            cookie.setMaxAge(LoginService.SESSION_TIME);
        }
        response.addCookie(cookie);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, sessionKey);
    }

    @GetMapping("/sysUser")
    public ResultDTO<User> sysUser(HttpServletRequest request) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
                loginService.sysUser(CookieUtils.getOneSessionKey(request)));
    }

    @PostMapping("/editPassword")
    public ResultDTO<Boolean> editPassword(HttpServletRequest request, @RequestBody EditPasswordReq editPasswordReq) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
                loginService.editPassword(CookieUtils.getOneSessionKey(request), editPasswordReq.getOldPassword(),
                        editPasswordReq.getNewPassword()));
    }

    @PostMapping("/logout")
    public ResultDTO<Boolean> logout(HttpServletRequest request, HttpServletResponse response) throws IOException {

        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
                loginService.logout(CookieUtils.getOneSessionKey(request)));
    }
}
