package com.luna.post.service.impl;

import java.io.IOException;

import com.luna.baidu.api.BaiduVoiceApi;
import com.luna.baidu.config.BaiduProperties;
import com.luna.baidu.req.VoiceSynthesisReq;
import com.luna.common.date.DateUtil;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.BaseException;
import com.luna.common.file.FileTools;
import com.luna.common.os.SystemInfoUtil;
import com.luna.common.text.RandomStrUtil;
import com.luna.post.config.LoginInterceptor;
import com.luna.post.dto.PostDTO;
import com.luna.post.dto.PostDeatilDTO;
import com.luna.post.dto.ShowUserDTO;
import com.luna.post.entity.*;
import com.luna.post.mapper.*;
import com.luna.post.service.AudioService;
import com.luna.post.service.PostService;
import com.luna.post.tools.UserTools;
import com.luna.post.user.UserManager;
import com.luna.post.utils.DO2DTOUtil;
import com.luna.post.utils.FileUploadUtils;
import com.luna.redis.util.RedisHashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: luna
 * @CreateTime: 2021-05-27 17:19:51
 */
@Service
public class PostServiceImpl implements PostService {

    @Resource
    private PostMapper          postMapper;

    @Resource
    private CommentMapper       commentMapper;

    @Resource
    private UserMapper          userMapper;

    @Resource
    private RegisterMapper      registerMapper;

    @Resource
    private AudioMapper         audioMapper;

    @Resource
    private UserManager         userManager;

    @Resource
    private CommentPraiseMapper commentPraiseMapper;

    @Resource
    private UserTools           userTools;

    @Resource
    private BaiduProperties     baiduProperties;

    @Autowired
    private AudioService        audioService;

    @Override
    public PostDTO getById(Long id) {
        Post post = postMapper.getById(id);
        return getByEntity(post);
    }

    @Override
    public PostDeatilDTO getDetail(Long id) {
        PostDTO postDTO = getById(id);
        User user = userMapper.getById(postDTO.getUserId());
        Register register = registerMapper.getByEntity(new Register(user.getId()));
        ShowUserDTO showUserDTO = DO2DTOUtil.user2ShowUserDTO(user, register);
        return new PostDeatilDTO(postDTO, showUserDTO);
    }

    @Override
    public PostDTO getByEntity(Post post) {
        Post postTemp = postMapper.getByEntity(post);
        User user = userMapper.getById(postTemp.getUserId());
        List<Comment> comments = commentMapper.listByEntity(new Comment(postTemp.getId()));
        Optional<Comment> first = comments.stream()
            .sorted(Comparator.comparing(Comment::getModifiedTime).reversed()).findFirst();
        CommentPraise comment = new CommentPraise(postTemp.getId());
        comment.setCommentId(0L);
        CommentPraise commentPraise = commentPraiseMapper.getByEntity(comment);
        if (commentPraise == null) {
            commentPraise = new CommentPraise(postTemp.getId());
            commentPraise.setPraise(0);
            commentPraise.setUserId(user.getId());
            commentPraise.setCommentId(0L);
            commentPraiseMapper.insert(commentPraise);
        }
        return DO2DTOUtil.postDO2PostDTO(postTemp, user.getName(),
            first.isPresent() ? first.get().getModifiedTime() : postTemp.getModifiedTime(), commentPraise.getPraise(),
            comments.size());
    }

    @Override
    public List<Post> listByEntity(Post post) {
        return postMapper.listByEntity(post);
    }

    @Override
    public PageInfo<PostDTO> listPageByEntity(String sessionKey, int page, int pageSize, Post post) {
        User user = userTools.getUser(sessionKey);

        if (Objects.equals(user.getAdmin(), "1")) {
            PageHelper.startPage(page, pageSize);
            return getPostDTOPageInfo(post);
        } else {
            PageHelper.startPage(page, pageSize);
            post.setUserId(user.getId());
            return getPostDTOPageInfo(post);
        }
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
        User user = userTools.getUser(sessionKey);
        post.setUserId(user.getId());
        post.setPostPageViews(0L);
        Audio audio = audioService.getAudio(user.getId());
        String path = audioService.changeVoice(audio, post.getPostText());
        post.setPostAudio(userManager.getPath() + "/" + path);
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
        for (Long i : list) {
            Post post = postMapper.getById(i);
            CommentPraise commentPraise = new CommentPraise();
            // post 的赞，对应的评论应为0
            commentPraise.setPostId(post.getId());
            commentPraise.setCommentId(0L);
            commentPraiseMapper.deleteByEntity(commentPraise);
        }
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
    public PageInfo<PostDTO> myListPageByEntity(String sessionKey, int page, int size, Post post) {
        User user = userTools.getUser(sessionKey);

        PageHelper.startPage(page, size);
        post.setUserId(user.getId());
        return getPostDTOPageInfo(post);
    }

    @Override
    public synchronized Integer praise(Long postId) {
        CommentPraise tempCommentPraise = new CommentPraise(postId);
        tempCommentPraise.setCommentId(0L);
        CommentPraise commentPraise = commentPraiseMapper.getByEntity(tempCommentPraise);
        commentPraise.setPraise(commentPraise.getPraise() + 1);
        commentPraiseMapper.update(commentPraise);
        return commentPraise.getPraise();
    }

    @Override
    public synchronized Boolean read(Long postId) {
        Post post = postMapper.getById(postId);
        post.setPostPageViews(post.getPostPageViews() + 1);
        return postMapper.update(post) == 1;
    }

    private PageInfo<PostDTO> getPostDTOPageInfo(Post post) {
        List<Post> list = postMapper.listByEntity(post);
        PageInfo<PostDTO> pageInfo = new PageInfo(list);
        List<PostDTO> collect = list.stream().map(this::getByEntity).collect(Collectors.toList());
        pageInfo.setList(collect);
        return pageInfo;
    }
}
