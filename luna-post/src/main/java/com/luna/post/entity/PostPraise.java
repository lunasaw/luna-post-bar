package com.luna.post.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 文章扩展表
 * (PostPraise)实体类
 *
 * @author luna
 * @since 2021-05-31 16:34:55
 */
public class PostPraise implements Serializable {
    private static final long serialVersionUID = -26943806761632923L;
    /** 创建编号 */
    private Long              id;
    /** 文章编号 */
    private Long              postId;
    /** 用户编号 */
    private Long              userId;
    /** 创建时间 */
    private Date              createTime;
    /** 修改时间 */
    private Date              modifiedTime;
    /** 锁 */
    private Long              version;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
