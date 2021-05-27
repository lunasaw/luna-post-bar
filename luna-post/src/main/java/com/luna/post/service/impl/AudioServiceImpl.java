package com.luna.post.service.impl;

import com.luna.post.mapper.AudioMapper;
import com.luna.post.service.AudioService;
import com.luna.post.entity.Audio;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-05-27 17:20:43
 */
@Service
public class AudioServiceImpl implements AudioService {

    @Autowired
    private AudioMapper audioMapper;

    @Override
    public Audio getById(Long id) {
        return audioMapper.getById(id);
    }

    @Override
    public Audio getByEntity(Audio audio) {
        return audioMapper.getByEntity(audio);
    }

    @Override
    public List<Audio> listByEntity(Audio audio) {
        return audioMapper.listByEntity(audio);
    }

    @Override
    public PageInfo<Audio> listPageByEntity(int page, int pageSize, Audio audio) {
        PageHelper.startPage(page, pageSize);
        List<Audio> list = audioMapper.listByEntity(audio);
        return new PageInfo<Audio>(list);
    }

    @Override
    public PageInfo<Audio> listPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Audio> list = audioMapper.listByEntity(new Audio());
        return new PageInfo<Audio>(list);
    }

    @Override
    public List<Audio> listByIds(List<Long> ids) {
        return audioMapper.listByIds(ids);
    }

    @Override
    public int insert(Audio audio) {
        return audioMapper.insert(audio);
    }

    @Override
    public int insertBatch(List<Audio> list) {
        return audioMapper.insertBatch(list);
    }

    @Override
    public int update(Audio audio) {
        return audioMapper.update(audio);
    }

    @Override
    public int updateBatch(List<Audio> list) {
        return audioMapper.updateBatch(list);
    }

    @Override
    public int deleteById(Long id) {
        return audioMapper.deleteById(id);
    }

    @Override
    public int deleteByEntity(Audio audio) {
        return audioMapper.deleteByEntity(audio);
    }

    @Override
    public int deleteByIds(List<Long> list) {
        return audioMapper.deleteByIds(list);
    }

    @Override
    public int countAll() {
        return audioMapper.countAll();
    }

    @Override
    public int countByEntity(Audio audio) {
        return audioMapper.countByEntity(audio);
    }

}

