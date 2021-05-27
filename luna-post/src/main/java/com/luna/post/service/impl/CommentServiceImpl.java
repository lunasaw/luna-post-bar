package com.luna.post.service.impl;

import com.luna.post.mapper.CommentMapper;
import com.luna.post.service.CommentService;
import com.luna.post.entity.Comment;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-05-27 17:20:27
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Comment getById(Long id) {
        return commentMapper.getById(id);
    }

    @Override
    public Comment getByEntity(Comment comment) {
        return commentMapper.getByEntity(comment);
    }

    @Override
    public List<Comment> listByEntity(Comment comment) {
        return commentMapper.listByEntity(comment);
    }

    @Override
    public PageInfo<Comment> listPageByEntity(int page, int pageSize, Comment comment) {
        PageHelper.startPage(page, pageSize);
        List<Comment> list = commentMapper.listByEntity(comment);
        return new PageInfo<Comment>(list);
    }

    @Override
    public PageInfo<Comment> listPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Comment> list = commentMapper.listByEntity(new Comment());
        return new PageInfo<Comment>(list);
    }

    @Override
    public List<Comment> listByIds(List<Long> ids) {
        return commentMapper.listByIds(ids);
    }

    @Override
    public int insert(Comment comment) {
        return commentMapper.insert(comment);
    }

    @Override
    public int insertBatch(List<Comment> list) {
        return commentMapper.insertBatch(list);
    }

    @Override
    public int update(Comment comment) {
        return commentMapper.update(comment);
    }

    @Override
    public int updateBatch(List<Comment> list) {
        return commentMapper.updateBatch(list);
    }

    @Override
    public int deleteById(Long id) {
        return commentMapper.deleteById(id);
    }

    @Override
    public int deleteByEntity(Comment comment) {
        return commentMapper.deleteByEntity(comment);
    }

    @Override
    public int deleteByIds(List<Long> list) {
        return commentMapper.deleteByIds(list);
    }

    @Override
    public int countAll() {
        return commentMapper.countAll();
    }

    @Override
    public int countByEntity(Comment comment) {
        return commentMapper.countByEntity(comment);
    }

}

