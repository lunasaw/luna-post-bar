package com.luna.post.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 评论
 * (Comment)实体类
 *
 * @author luna
 * @since 2021-05-27 17:20:27
 */
public class Comment implements Serializable {
    private static final long serialVersionUID = -97114259141134378L;
    /**
     * 文章编号
     */
    private Long id;
    /**
     * 文章编号
     */
    private Long postId;
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论音频
     */
    private String audio;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifiedTime;
    /**
     * 锁
     */
    private Long version;


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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
