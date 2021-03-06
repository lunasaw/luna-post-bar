package com.luna.post.service;

import com.luna.post.mapper.CommentPraiseMapper;
import com.luna.post.entity.CommentPraise;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-05-28 22:17:26
 */
public interface CommentPraiseService {

    /**
     * 通过主键查询数据
     *
     * @param id 主键
     * @return 对象
     */
    CommentPraise getById(Long id);

    /**
     * 通过实体不为空的属性作为筛选条件查询单个
     *
     * @param commentPraise 条件
     * @return 对象
     */
    CommentPraise getByEntity(CommentPraise commentPraise);

    /**
     * 通过实体不为空的属性作为筛选条件查询列表
     *
     * @param commentPraise 条件
     * @return 对象列表
     */
    List<CommentPraise> listByEntity(CommentPraise commentPraise);

    /**
     * 条件分页查询
     *
     * @param commentPraise 查询条件
     * @param page 起始标号
     * @param pageSize 查询条目
     * @return 对象列表
     */
    PageInfo<CommentPraise> listPageByEntity(int page, int pageSize, CommentPraise commentPraise);

    /**
     * 条件分页查询
     *
     * @param page 起始标号
     * @param pageSize 查询条目
     * @return 对象列表
     */
    PageInfo<CommentPraise> listPage(int page, int pageSize);

    /**
     * Id列表查询对象列表
     *
     * @param ids Id列表
     * @return 对象列表
     */
    List<CommentPraise> listByIds(List<Long> ids);

    /**
     * 插入
     *
     * @param commentPraise 对象
     * @return 影响行数
     */
    int insert(CommentPraise commentPraise);

    /**
     * 列表插入
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int insertBatch(List<CommentPraise> list);

    /**
     * 更新
     *
     * @param commentPraise 对象
     * @return 影响行数
     */
    int update(CommentPraise commentPraise);

    /**
     * 列表更新
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int updateBatch(List<CommentPraise> list);

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
     * @param commentPraise 对象
     * @return 影响行数
     */
    int deleteByEntity(CommentPraise commentPraise);

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
     * @param commentPraise 对象
     * @return 影响行数
     */
    int countByEntity(CommentPraise commentPraise);
}
