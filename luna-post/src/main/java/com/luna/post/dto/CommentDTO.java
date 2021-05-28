package com.luna.post.dto;

import java.io.Serializable;

/**
 * 评论
 * (Comment)实体类
 *
 * @author luna
 * @since 2021-05-27 17:20:27
 */
public class CommentDTO implements Serializable {
    private static final long serialVersionUID = -97114259141134378L;
    /**
     * 文章编号
     */
    private Long              id;
    /**
     * 文章编号
     */
    private Long              postId;

    /**
     * 评论人
     */
    private String            username;

    /**
     * 评论人注册时间
     */
    private String            userTime;

    /**
     * 评论人头像
     */
    private String            photo;

    /**
     * 用户编号
     */
    private String            userId;
    /**
     * 评论内容
     */
    private String            content;
    /**
     * 评论音频
     */
    private String            audio;
    /**
     * 创建时间
     */
    private String            createTime;
    /**
     * 修改时间
     */
    private String            modifiedTime;

    /**
     * 赞个数
     */
    private Integer           postPraise;
    /**
     * 锁
     */
    private Long              version;

    public Integer getPostPraise() {
        return postPraise;
    }

    public void setPostPraise(Integer postPraise) {
        this.postPraise = postPraise;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserTime() {
        return userTime;
    }

    public void setUserTime(String userTime) {
        this.userTime = userTime;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public CommentDTO(Long postId) {
        this.postId = postId;
    }

    public CommentDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
