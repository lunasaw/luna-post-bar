package com.luna.post.req;

/**
 * @author luna@mac
 * 2021年05月06日 18:45
 */
public class EditPasswordReq {

    private String newPassword;

    private String oldPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
