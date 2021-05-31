package com.luna.post.service.impl;

import com.luna.post.mapper.PostPraiseMapper;
import com.luna.post.service.PostPraiseService;
import com.luna.post.entity.PostPraise;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.Date;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-05-31 16:34:55
 */
@Service
public class PostPraiseServiceImpl implements PostPraiseService {

    @Autowired
    private PostPraiseMapper postPraiseMapper;

    @Override
    public PostPraise getById(Long id) {
        return postPraiseMapper.getById(id);
    }

    @Override
    public PostPraise getByEntity(PostPraise postPraise) {
        return postPraiseMapper.getByEntity(postPraise);
    }

    @Override
    public List<PostPraise> listByEntity(PostPraise postPraise) {
        return postPraiseMapper.listByEntity(postPraise);
    }

    @Override
    public PageInfo<PostPraise> listPageByEntity(int page, int pageSize, PostPraise postPraise) {
        PageHelper.startPage(page, pageSize);
        List<PostPraise> list = postPraiseMapper.listByEntity(postPraise);
        return new PageInfo<PostPraise>(list);
    }

    @Override
    public PageInfo<PostPraise> listPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<PostPraise> list = postPraiseMapper.listByEntity(new PostPraise());
        return new PageInfo<PostPraise>(list);
    }

    @Override
    public List<PostPraise> listByIds(List<Long> ids) {
        return postPraiseMapper.listByIds(ids);
    }

    @Override
    public int insert(PostPraise postPraise) {
        return postPraiseMapper.insert(postPraise);
    }

    @Override
    public int insertBatch(List<PostPraise> list) {
        return postPraiseMapper.insertBatch(list);
    }

    @Override
    public int update(PostPraise postPraise) {
        return postPraiseMapper.update(postPraise);
    }

    @Override
    public int updateBatch(List<PostPraise> list) {
        return postPraiseMapper.updateBatch(list);
    }

    @Override
    public int deleteById(Long id) {
        return postPraiseMapper.deleteById(id);
    }

    @Override
    public int deleteByEntity(PostPraise postPraise) {
        return postPraiseMapper.deleteByEntity(postPraise);
    }

    @Override
    public int deleteByIds(List<Long> list) {
        return postPraiseMapper.deleteByIds(list);
    }

    @Override
    public int countAll() {
        return postPraiseMapper.countAll();
    }

    @Override
    public int countByEntity(PostPraise postPraise) {
        return postPraiseMapper.countByEntity(postPraise);
    }

}
