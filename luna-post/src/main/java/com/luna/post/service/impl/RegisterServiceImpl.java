package com.luna.post.service.impl;

import com.luna.post.mapper.RegisterMapper;
import com.luna.post.service.RegisterService;
import com.luna.post.entity.Register;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-05-27 17:19:07
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterMapper registerMapper;

    @Override
    public Register getById(Long id) {
        return registerMapper.getById(id);
    }

    @Override
    public Register getByEntity(Register register) {
        return registerMapper.getByEntity(register);
    }

    @Override
    public List<Register> listByEntity(Register register) {
        return registerMapper.listByEntity(register);
    }

    @Override
    public PageInfo<Register> listPageByEntity(int page, int pageSize, Register register) {
        PageHelper.startPage(page, pageSize);
        List<Register> list = registerMapper.listByEntity(register);
        return new PageInfo<Register>(list);
    }

    @Override
    public PageInfo<Register> listPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Register> list = registerMapper.listByEntity(new Register());
        return new PageInfo<Register>(list);
    }

    @Override
    public List<Register> listByIds(List<Long> ids) {
        return registerMapper.listByIds(ids);
    }

    @Override
    public int insert(Register register) {
        return registerMapper.insert(register);
    }

    @Override
    public int insertBatch(List<Register> list) {
        return registerMapper.insertBatch(list);
    }

    @Override
    public int update(Register register) {
        return registerMapper.update(register);
    }

    @Override
    public int updateBatch(List<Register> list) {
        return registerMapper.updateBatch(list);
    }

    @Override
    public int deleteById(Long id) {
        return registerMapper.deleteById(id);
    }

    @Override
    public int deleteByEntity(Register register) {
        return registerMapper.deleteByEntity(register);
    }

    @Override
    public int deleteByIds(List<Long> list) {
        return registerMapper.deleteByIds(list);
    }

    @Override
    public int countAll() {
        return registerMapper.countAll();
    }

    @Override
    public int countByEntity(Register register) {
        return registerMapper.countByEntity(register);
    }

}

