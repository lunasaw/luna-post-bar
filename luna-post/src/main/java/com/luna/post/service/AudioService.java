package com.luna.post.service;

import com.luna.post.dto.AudioDTO;
import com.luna.post.entity.Audio;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-05-27 17:20:43
 */
public interface AudioService {

    /**
     * 文字转语音
     * 
     * @param audio 语音配置
     * @param text 文字
     * @return
     */
    public String changeVoice(Audio audio, String text);

    /**
     * 用户编号查询声音配置
     * 
     * @param userId
     * @return
     */
    Audio getAudio(Long userId);

    /**
     * 通过主键查询数据
     *
     * @param id 主键
     * @return 对象
     */
    Audio getById(Long id);

    /**
     * 通过实体不为空的属性作为筛选条件查询单个
     *
     *
     * @param oneSessionKey
     * @param audio 条件
     * @return 对象
     */
    Audio getByEntity(String oneSessionKey, Audio audio);

    /**
     * 通过实体不为空的属性作为筛选条件查询列表
     *
     * @param audio 条件
     * @return 对象列表
     */
    List<Audio> listByEntity(Audio audio);

    /**
     * 条件分页查询
     *
     * @param audio 查询条件
     * @param page 起始标号
     * @param pageSize 查询条目
     * @return 对象列表
     */
    PageInfo<Audio> listPageByEntity(int page, int pageSize, Audio audio);

    /**
     * 条件分页查询
     *
     * @param page 起始标号
     * @param pageSize 查询条目
     * @return 对象列表
     */
    PageInfo<Audio> listPage(int page, int pageSize);

    /**
     * Id列表查询对象列表
     *
     * @param ids Id列表
     * @return 对象列表
     */
    List<Audio> listByIds(List<Long> ids);

    /**
     * 插入
     *
     *
     * @param oneSessionKey
     * @param audioDTO 对象
     * @return 影响行数
     */
    int insert(String oneSessionKey, AudioDTO audioDTO);

    /**
     * 列表插入
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int insertBatch(List<Audio> list);

    /**
     * 更新
     *
     * @param audioDTO
     * @return 影响行数
     */
    int update(String sessionKey, AudioDTO audioDTO);

    /**
     * 列表更新
     *
     * @param list 列表对象
     * @return 影响行数
     */
    int updateBatch(List<Audio> list);

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
     * @param audio 对象
     * @return 影响行数
     */
    int deleteByEntity(Audio audio);

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
     * @param audio 对象
     * @return 影响行数
     */
    int countByEntity(Audio audio);
}
