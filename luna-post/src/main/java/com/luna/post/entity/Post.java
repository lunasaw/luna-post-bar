package com.luna.post.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 文章帖子
 * (Post)实体类
 *
 * @author luna
 * @since 2021-05-27 17:19:51
 */
public class Post implements Serializable {
    private static final long serialVersionUID = 676938612085295023L;
    private Long id;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 文章标题
     */
    private String postTitle;
    /**
     * 文章内容
     */
    private String postText;
    /**
     * 文章阅读数
     */
    private Long postPageViews;
    /**
     * 文章音频
     */
    private String postAudio;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public Long getPostPageViews() {
        return postPageViews;
    }

    public void setPostPageViews(Long postPageViews) {
        this.postPageViews = postPageViews;
    }

    public String getPostAudio() {
        return postAudio;
    }

    public void setPostAudio(String postAudio) {
        this.postAudio = postAudio;
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
