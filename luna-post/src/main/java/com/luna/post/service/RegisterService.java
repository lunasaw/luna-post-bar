package com.luna.post.service;

import com.luna.post.mapper.RegisterMapper;
import com.luna.post.entity.Register;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-05-27 17:19:06
 */
public interface RegisterService {

    /**
     * 通过主键查询数据
     *
     * @param id 主键
     * @return 对象
     */
    Register getById(Long id);

    /**
     * 通过实体不为空的属性作为筛选条件查询单个
     *
     * @param register 条件
     * @return 对象
     */
    Register getByEntity(Register register);

    /**
     * 通过实体不为空的属性作为筛选条件查询列表
     *
     * @param register 条件
     * @return 对象列表
     */
    List<Register> listByEntity(Register register);

    /**
     * 条件分页查询
     *
     * @param register 查询条件
     * @param page     起始标号
     * @param pageSize 查询条目
     * @return 对象列表
     */
    PageInfo<Register> listPageByEntity(int page, int pageSize, Register register);

    /**
     * 条件分页查询
     *
     * @param page     起始标号
     * @param pageSize 查询条目
     * @return 对象列表
     */
    PageInfo<Register> listPage(int page, int pageSize);

    /**
     * Id列表查询对象列表
     *
     * @param ids Id列表
     * @return 对象列表
     */
    List<Register> listByIds(List<Long> ids);

    /**
     * 插入
     *
     * @param register 对象
     * @return 影响行数
     */
    int insert(Register register);

    /**
     * 列表插入
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int insertBatch(List<Register> list);

    /**
     * 更新
     *
     * @param register 对象
     * @return 影响行数
     */
    int update(Register register);

    /**
     * 列表更新
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int updateBatch(List<Register> list);

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
     * @param register 对象
     * @return 影响行数
     */
    int deleteByEntity(Register register);

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
     * @param register 对象
     * @return 影响行数
     */
    int countByEntity(Register register);
}
