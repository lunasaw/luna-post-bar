package com.luna.post.service;

import com.luna.post.mapper.PostPraiseMapper;
import com.luna.post.entity.PostPraise;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-05-31 16:34:55
 */
public interface PostPraiseService {

    /**
     * 通过主键查询数据
     *
     * @param id 主键
     * @return 对象
     */
    PostPraise getById(Long id);

    /**
     * 通过实体不为空的属性作为筛选条件查询单个
     *
     * @param postPraise 条件
     * @return 对象
     */
    PostPraise getByEntity(PostPraise postPraise);

    /**
     * 通过实体不为空的属性作为筛选条件查询列表
     *
     * @param postPraise 条件
     * @return 对象列表
     */
    List<PostPraise> listByEntity(PostPraise postPraise);

    /**
     * 条件分页查询
     *
     * @param postPraise 查询条件
     * @param page 起始标号
     * @param pageSize 查询条目
     * @return 对象列表
     */
    PageInfo<PostPraise> listPageByEntity(int page, int pageSize, PostPraise postPraise);

    /**
     * 条件分页查询
     *
     * @param page 起始标号
     * @param pageSize 查询条目
     * @return 对象列表
     */
    PageInfo<PostPraise> listPage(int page, int pageSize);

    /**
     * Id列表查询对象列表
     *
     * @param ids Id列表
     * @return 对象列表
     */
    List<PostPraise> listByIds(List<Long> ids);

    /**
     * 插入
     *
     * @param postPraise 对象
     * @return 影响行数
     */
    int insert(PostPraise postPraise);

    /**
     * 列表插入
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int insertBatch(List<PostPraise> list);

    /**
     * 更新
     *
     * @param postPraise 对象
     * @return 影响行数
     */
    int update(PostPraise postPraise);

    /**
     * 列表更新
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int updateBatch(List<PostPraise> list);

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
     * @param postPraise 对象
     * @return 影响行数
     */
    int deleteByEntity(PostPraise postPraise);

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
     * @param postPraise 对象
     * @return 影响行数
     */
    int countByEntity(PostPraise postPraise);
}
