package com.luna.post.req;

import javax.validation.constraints.NotNull;

/**
 * @author luna@mac
 * 2021年04月29日 10:00
 */
public class LoginReq {

    @NotNull
    private String username;

    private String password;

    private String rememberPwd;

    public String getRememberPwd() {
        return rememberPwd;
    }

    public void setRememberPwd(String rememberPwd) {
        this.rememberPwd = rememberPwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
