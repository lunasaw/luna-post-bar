package com.luna.post.service;

import com.luna.post.dto.CommentDTO;
import com.luna.post.entity.Comment;
import com.github.pagehelper.PageInfo;
import com.luna.post.entity.CommentPraise;

import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-05-27 17:20:27
 */
public interface CommentService {

    /**
     * 通过主键查询数据
     *
     * @param id 主键
     * @return 对象
     */
    Comment getById(Long id);

    /**
     * 通过实体不为空的属性作为筛选条件查询单个
     *
     * @param comment 条件
     * @return 对象
     */
    Comment getByEntity(Comment comment);

    /**
     * 通过实体不为空的属性作为筛选条件查询列表
     *
     * @param comment 条件
     * @return 对象列表
     */
    List<CommentDTO> listByEntity(Comment comment);

    /**
     * 获取热点评论
     *
     * @return
     */
    CommentDTO getHot(Comment comment);

    /**
     * 条件分页查询
     *
     * @param comment 查询条件
     * @param page 起始标号
     * @param pageSize 查询条目
     * @return 对象列表
     */
    PageInfo<Comment> listPageByEntity(int page, int pageSize, Comment comment);

    /**
     * 条件分页查询
     *
     * @param page 起始标号
     * @param pageSize 查询条目
     * @return 对象列表
     */
    PageInfo<Comment> listPage(int page, int pageSize);

    /**
     * Id列表查询对象列表
     *
     * @param ids Id列表
     * @return 对象列表
     */
    List<Comment> listByIds(List<Long> ids);

    /**
     * 插入
     *
     *
     * @param oneSessionKey
     * @param comment 对象
     * @return 影响行数
     */
    int insert(String oneSessionKey, Comment comment);

    /**
     * 列表插入
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int insertBatch(List<Comment> list);

    /**
     * 更新
     *
     * @param comment 对象
     * @return 影响行数
     */
    int update(Comment comment);

    /**
     * 列表更新
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int updateBatch(List<Comment> list);

    /**
     * 删除
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 条件删除
     *
     * @param comment 对象
     * @return 影响行数
     */
    int deleteByEntity(Comment comment);

    /**
     * 主键列表删除
     *
     * @param list 主键列表
     * @return 影响行数
     */
    int deleteByIds(List<Long> list);

    /**
     * 数据条目
     *
     * @return 影响行数
     */
    int countAll();

    /**
     * 条件查询数目
     *
     * @param comment 对象
     * @return 影响行数
     */
    int countByEntity(Comment comment);

    CommentPraise praise(Long postId, Long commentId);
}
