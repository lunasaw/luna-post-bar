package com.luna.post.dto;

/**
 * @author luna
 * 2021/5/28
 */
public class ShowUserDTO {

    /**
     * 用户ID
     */
    private Long    id;
    /**
     * 用户名
     */
    private String  name;
    /**
     * 密码
     */
    private String  password;
    /**
     * 上次登陆时间
     */
    private String  loginTime;
    /**
     * 是否管理员
     */
    private String  admin;
    /**
     * 创建时间
     */
    private String  createTime;
    /**
     * 性别
     */
    private String  sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 邮箱
     */
    private String  email;
    /**
     * 头像
     */
    private String  photo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
