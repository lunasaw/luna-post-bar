package com.luna.post.service.impl;

import com.luna.post.mapper.CommentPraiseMapper;
import com.luna.post.service.CommentPraiseService;
import com.luna.post.entity.CommentPraise;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.Date;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-05-28 22:17:26
 */
@Service
public class CommentPraiseServiceImpl implements CommentPraiseService {

    @Autowired
    private CommentPraiseMapper commentPraiseMapper;

    @Override
    public CommentPraise getById(Long id) {
        return commentPraiseMapper.getById(id);
    }

    @Override
    public CommentPraise getByEntity(CommentPraise commentPraise) {
        return commentPraiseMapper.getByEntity(commentPraise);
    }

    @Override
    public List<CommentPraise> listByEntity(CommentPraise commentPraise) {
        return commentPraiseMapper.listByEntity(commentPraise);
    }

    @Override
    public PageInfo<CommentPraise> listPageByEntity(int page, int pageSize, CommentPraise commentPraise) {
        PageHelper.startPage(page, pageSize);
        List<CommentPraise> list = commentPraiseMapper.listByEntity(commentPraise);
        return new PageInfo<CommentPraise>(list);
    }

    @Override
    public PageInfo<CommentPraise> listPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<CommentPraise> list = commentPraiseMapper.listByEntity(new CommentPraise());
        return new PageInfo<CommentPraise>(list);
    }

    @Override
    public List<CommentPraise> listByIds(List<Long> ids) {
        return commentPraiseMapper.listByIds(ids);
    }

    @Override
    public int insert(CommentPraise commentPraise) {
        return commentPraiseMapper.insert(commentPraise);
    }

    @Override
    public int insertBatch(List<CommentPraise> list) {
        return commentPraiseMapper.insertBatch(list);
    }

    @Override
    public int update(CommentPraise commentPraise) {
        return commentPraiseMapper.update(commentPraise);
    }

    @Override
    public int updateBatch(List<CommentPraise> list) {
        return commentPraiseMapper.updateBatch(list);
    }

    @Override
    public int deleteById(Long id) {
        return commentPraiseMapper.deleteById(id);
    }

    @Override
    public int deleteByEntity(CommentPraise commentPraise) {
        return commentPraiseMapper.deleteByEntity(commentPraise);
    }

    @Override
    public int deleteByIds(List<Long> list) {
        return commentPraiseMapper.deleteByIds(list);
    }

    @Override
    public int countAll() {
        return commentPraiseMapper.countAll();
    }

    @Override
    public int countByEntity(CommentPraise commentPraise) {
        return commentPraiseMapper.countByEntity(commentPraise);
    }

}
