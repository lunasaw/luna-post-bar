package com.luna.post.service;

import com.luna.post.dto.PostDTO;
import com.luna.post.entity.Post;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-05-27 17:19:51
 */
public interface PostService {

    /**
     * 通过主键查询数据
     *
     * @param id 主键
     * @return 对象
     */
    Post getById(Long id);

    /**
     * 通过实体不为空的属性作为筛选条件查询单个
     *
     * @param post 条件
     * @return 对象
     */
    Post getByEntity(Post post);

    /**
     * 通过实体不为空的属性作为筛选条件查询列表
     *
     * @param post 条件
     * @return 对象列表
     */
    List<Post> listByEntity(Post post);

    /**
     * 条件分页查询
     *
     * @param post 查询条件
     * @param page 起始标号
     * @param pageSize 查询条目
     * @return 对象列表
     */
    PageInfo<PostDTO> listPageByEntity(int page, int pageSize, Post post);

    /**
     * 条件分页查询
     *
     * @param page 起始标号
     * @param pageSize 查询条目
     * @return 对象列表
     */
    PageInfo<Post> listPage(int page, int pageSize);

    /**
     * Id列表查询对象列表
     *
     * @param ids Id列表
     * @return 对象列表
     */
    List<Post> listByIds(List<Long> ids);

    /**
     * 插入
     *
     *
     * @param oneSessionKey
     * @param post 对象
     * @return 影响行数
     */
    int insert(String oneSessionKey, Post post);

    /**
     * 列表插入
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int insertBatch(List<Post> list);

    /**
     * 更新
     *
     * @param post 对象
     * @return 影响行数
     */
    int update(Post post);

    /**
     * 列表更新
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int updateBatch(List<Post> list);

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
     * @param post 对象
     * @return 影响行数
     */
    int deleteByEntity(Post post);

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
     * @param post 对象
     * @return 影响行数
     */
    int countByEntity(Post post);

    /**
     * 查询本人发布的文章
     * 
     * @param oneSessionKey
     * @param page
     * @param size
     * @param post
     * @return
     */
    PageInfo<PostDTO> MyListPageByEntity(String oneSessionKey, int page, int size, Post post);
}
