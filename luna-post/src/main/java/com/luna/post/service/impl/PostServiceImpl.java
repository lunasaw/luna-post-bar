package com.luna.post.service.impl;

import com.luna.post.mapper.PostMapper;
import com.luna.post.service.PostService;
import com.luna.post.entity.Post;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-05-27 17:19:51
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    public Post getById(Long id) {
        return postMapper.getById(id);
    }

    @Override
    public Post getByEntity(Post post) {
        return postMapper.getByEntity(post);
    }

    @Override
    public List<Post> listByEntity(Post post) {
        return postMapper.listByEntity(post);
    }

    @Override
    public PageInfo<Post> listPageByEntity(int page, int pageSize, Post post) {
        PageHelper.startPage(page, pageSize);
        List<Post> list = postMapper.listByEntity(post);
        return new PageInfo<Post>(list);
    }

    @Override
    public PageInfo<Post> listPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Post> list = postMapper.listByEntity(new Post());
        return new PageInfo<Post>(list);
    }

    @Override
    public List<Post> listByIds(List<Long> ids) {
        return postMapper.listByIds(ids);
    }

    @Override
    public int insert(Post post) {
        return postMapper.insert(post);
    }

    @Override
    public int insertBatch(List<Post> list) {
        return postMapper.insertBatch(list);
    }

    @Override
    public int update(Post post) {
        return postMapper.update(post);
    }

    @Override
    public int updateBatch(List<Post> list) {
        return postMapper.updateBatch(list);
    }

    @Override
    public int deleteById(Long id) {
        return postMapper.deleteById(id);
    }

    @Override
    public int deleteByEntity(Post post) {
        return postMapper.deleteByEntity(post);
    }

    @Override
    public int deleteByIds(List<Long> list) {
        return postMapper.deleteByIds(list);
    }

    @Override
    public int countAll() {
        return postMapper.countAll();
    }

    @Override
    public int countByEntity(Post post) {
        return postMapper.countByEntity(post);
    }

}

