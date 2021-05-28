package com.luna.post.service.impl;

import com.luna.common.dto.constant.ResultCode;
import com.luna.post.config.LoginInterceptor;
import com.luna.post.dto.PostDTO;
import com.luna.post.entity.Comment;
import com.luna.post.entity.User;
import com.luna.post.entity.UserException;
import com.luna.post.mapper.CommentMapper;
import com.luna.post.mapper.PostMapper;
import com.luna.post.mapper.UserMapper;
import com.luna.post.service.PostService;
import com.luna.post.entity.Post;
import com.luna.post.utils.DO2DTOUtil;
import com.luna.redis.util.RedisHashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: luna
 * @CreateTime: 2021-05-27 17:19:51
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper    postMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper    userMapper;

    @Autowired
    private RedisHashUtil redisHashUtil;

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
    public PageInfo<PostDTO> listPageByEntity(int page, int pageSize, Post post) {
        PageHelper.startPage(page, pageSize);
        return getPostDTOPageInfo(post);
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
    public int insert(String sessionKey, Post post) {
        if (sessionKey == null) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "用户不存在");
        }
        User user = (User)redisHashUtil.get(LoginInterceptor.sessionKey + ":" + sessionKey, sessionKey);
        post.setUserId(user.getId());

        // TODO 这里需要讲内容转为语音
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

    @Override
    public PageInfo<PostDTO> MyListPageByEntity(String sessionKey, int page, int size, Post post) {
        if (sessionKey == null) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "用户不存在");
        }
        User user = (User)redisHashUtil.get(LoginInterceptor.sessionKey + ":" + sessionKey, sessionKey);
        PageHelper.startPage(page, size);
        post.setUserId(user.getId());
        return getPostDTOPageInfo(post);
    }

    private PageInfo<PostDTO> getPostDTOPageInfo(Post post) {
        List<Post> list = postMapper.listByEntity(post);
        PageInfo<PostDTO> pageInfo = new PageInfo(list);
        List<PostDTO> collect = list.stream().map(postTemp -> {
            int i = commentMapper.countByEntity(new Comment(postTemp.getId()));
            User byId = userMapper.getById(postTemp.getUserId());
            List<Comment> comments = commentMapper.listByEntity(new Comment(postTemp.getId()));
            Optional<Comment> first = comments.stream()
                .sorted(Comparator.comparing(Comment::getModifiedTime).reversed()).findFirst();
            return DO2DTOUtil.postDO2PostDTO(postTemp, byId.getName(),
                first.isPresent() ? first.get().getModifiedTime() : postTemp.getModifiedTime(), i);
        }).collect(Collectors.toList());
        pageInfo.setList(collect);
        return pageInfo;
    }
}
