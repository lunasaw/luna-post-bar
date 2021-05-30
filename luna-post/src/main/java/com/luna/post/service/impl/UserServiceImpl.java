package com.luna.post.service.impl;

import java.util.Date;

import com.luna.common.encrypt.HashTools;
import com.luna.post.entity.Comment;
import com.luna.post.entity.CommentPraise;
import com.luna.post.entity.Register;
import com.luna.post.mapper.RegisterMapper;
import com.luna.post.mapper.UserMapper;
import com.luna.post.service.UserService;
import com.luna.post.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-05-27 17:16:45
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper     userMapper;

    @Resource
    private RegisterMapper registerMapper;

    @Override
    public User getById(Long id) {
        return userMapper.getById(id);
    }

    @Override
    public User getByEntity(User user) {
        return userMapper.getByEntity(user);
    }

    @Override
    public List<User> listByEntity(User user) {
        return userMapper.listByEntity(user);
    }

    @Override
    public PageInfo<User> listPageByEntity(int page, int pageSize, User user) {
        PageHelper.startPage(page, pageSize);
        List<User> list = userMapper.listByEntity(user);
        return new PageInfo<User>(list);
    }

    @Override
    public PageInfo<User> listPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<User> list = userMapper.listByEntity(new User());
        return new PageInfo<User>(list);
    }

    @Override
    public List<User> listByIds(List<Long> ids) {
        return userMapper.listByIds(ids);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int insertBatch(List<User> list) {
        return userMapper.insertBatch(list);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int updateBatch(List<User> list) {
        return userMapper.updateBatch(list);
    }

    @Override
    public int deleteById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int deleteByEntity(User user) {
        return userMapper.deleteByEntity(user);
    }

    @Override
    public int deleteByIds(List<Long> list) {

        list.forEach(i -> {
            User user = userMapper.getById(i);
            registerMapper.deleteByEntity(new Register(user.getId()));
        });

        return userMapper.deleteByIds(list);
    }

    @Override
    public int countAll() {
        return userMapper.countAll();
    }

    @Override
    public int countByEntity(User user) {
        return userMapper.countByEntity(user);
    }

}
