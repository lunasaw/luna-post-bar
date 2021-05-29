package com.luna.post.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 评论扩展表(CommentPraise)实体类
 *
 * @author luna
 * @since 2021-05-28 22:17:26
 */
public class CommentPraise implements Serializable {
    private static final long serialVersionUID = 816177802327320521L;
    /** 评论id */
    private Long              id;
    /** 赞的数目? */
    private Integer           praise;
    /** 文章编号 */
    private Long              postId;
    /** 用户编号 */
    private Long              userId;
    /** 评论编号 */
    private Long              commentId;
    /** 创建时间 */
    private Date              createTime;
    /** 修改时间 */
    private Date              modifiedTime;
    /** 锁 */
    private Long              version;

    public CommentPraise(Long postId) {
        this.postId = postId;
    }

    public CommentPraise(Integer praise, Long postId, Long userId, Long commentId) {
        this.praise = praise;
        this.postId = postId;
        this.userId = userId;
        this.commentId = commentId;
    }

    public CommentPraise() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
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
