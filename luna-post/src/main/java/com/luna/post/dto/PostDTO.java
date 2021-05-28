package com.luna.post.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章帖子
 * (Post)实体类
 *
 * @author luna
 * @since 2021-05-27 17:19:51
 */
public class PostDTO implements Serializable {
    private static final long serialVersionUID = 676938612085295023L;
    private Long              id;
    /**
     * 用户编号
     */
    private Long              userId;
    /**
     * 创建人
     */
    private String            username;
    /**
     * 文章标题
     */
    private String            postTitle;
    /**
     * 文章内容
     */
    private String            postText;
    /**
     * 文章阅读数
     */
    private Long              postPageViews;

    /**
     * 文章评论数
     */
    private Integer           postCommentSize;
    /**
     * 文章音频
     */
    private String            postAudio;
    /**
     * 创建时间
     */
    private String            createTime;
    /**
     * 修改时间
     */
    private String            modifiedTime;
    /**
     * 最后评论时间
     */
    private String            lastComment;
    /**
     * 锁
     */
    private Long              version;

    public Integer getPostCommentSize() {
        return postCommentSize;
    }

    public void setPostCommentSize(Integer postCommentSize) {
        this.postCommentSize = postCommentSize;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastComment() {
        return lastComment;
    }

    public void setLastComment(String lastComment) {
        this.lastComment = lastComment;
    }
}
