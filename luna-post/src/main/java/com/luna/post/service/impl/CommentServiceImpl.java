package com.luna.post.service.impl;

import com.google.common.collect.Lists;
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
import com.luna.post.dto.CommentDTO;
import com.luna.post.entity.*;
import com.luna.post.mapper.*;
import com.luna.post.service.AudioService;
import com.luna.post.service.CommentService;

import com.luna.post.tools.UserTools;
import com.luna.post.user.UserManager;
import com.luna.post.utils.DO2DTOUtil;
import com.luna.post.utils.FileUploadUtils;
import com.luna.redis.util.RedisHashUtil;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: luna
 * @CreateTime: 2021-05-27 17:20:27
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper       commentMapper;

    @Resource
    private PostMapper          postMapper;

    @Resource
    private RegisterMapper      registerMapper;

    @Resource
    private UserMapper          userMapper;

    @Resource
    private CommentPraiseMapper commentPraiseMapper;

    @Resource
    private AudioService        audioService;

    @Resource
    private UserTools           userTools;

    @Override
    public Comment getById(Long id) {
        return commentMapper.getById(id);
    }

    @Override
    public Comment getByEntity(Comment comment) {
        return commentMapper.getByEntity(comment);
    }

    @Override
    public List<CommentDTO> listByEntity(Comment comment) {
        List<Comment> comments = commentMapper.listByEntity(comment);
        List<CommentDTO> collect = comments.stream().map(tempComment -> {
            // 这条评论的用户名
            CommentPraise commentPraise = new CommentPraise();
            commentPraise.setCommentId(tempComment.getId());
            CommentPraise praise = commentPraiseMapper.getByEntity(commentPraise);
            if (praise == null) {
                commentPraise.setPraise(0);
                commentPraise.setPostId(comment.getPostId());
                commentPraiseMapper.insert(commentPraise);
                praise = commentPraise;
            }
            User user = userMapper.getById(Long.valueOf(tempComment.getUserId()));
            Register register = registerMapper.getByEntity(new Register(user.getId()));
            Post post = postMapper.getById(tempComment.getPostId());
            return DO2DTOUtil.comment2CommentDTO(tempComment, user.getName(), user.getCreateTime(),
                register.getPhoto(), praise.getPraise(), post.getPostTitle());
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public PageInfo<CommentDTO> myListPageByEntity(String sessionKey, int page, int size, Comment comment) {
        User user = userTools.getUser(sessionKey);

        comment.setUserId(String.valueOf(user.getId()));
        PageHelper.startPage(page, size);
        List<Comment> list = commentMapper.listByEntity(comment);
        PageInfo<CommentDTO> pageInfo = new PageInfo(list);
        List<CommentDTO> collect = list.stream()
            .map(commentTemp -> {
                Post post = postMapper.getById(commentTemp.getPostId());
                return DO2DTOUtil.comment2CommentDTO(commentTemp, user.getName(), user.getCreateTime(), "", 0,
                    post.getPostTitle());
            })
            .collect(Collectors.toList());
        pageInfo.setList(collect);
        return pageInfo;
    }

    @Override
    public CommentDTO getHot(Comment comment) {
        List<CommentPraise> commentPraises = commentPraiseMapper.listByEntity(new CommentPraise(comment.getPostId()));

        Optional<CommentPraise> first =
            commentPraises.stream().filter(commentPraise -> commentPraise.getCommentId() != 0)
                .max(Comparator.comparing(CommentPraise::getPraise));
        if (first.isPresent()) {
            CommentPraise commentPraise = first.get();
            // 这条评论的用户名
            User user = userMapper.getById(commentPraise.getUserId());
            Register register = registerMapper.getByEntity(new Register(user.getId()));
            Comment tempComment = commentMapper.getById(commentPraise.getCommentId());
            Post post = postMapper.getById(tempComment.getPostId());
            return DO2DTOUtil.comment2CommentDTO(tempComment, user.getName(), user.getCreateTime(),
                register.getPhoto(), commentPraise.getPraise(), post.getPostTitle());
        }
        return null;
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
    public int insert(String sessionKey, Comment comment) {
        User user = userTools.getUser(sessionKey);

        comment.setUserId(String.valueOf(user.getId()));
        Audio audio = audioService.getAudio(user.getId());
        String path = audioService.changeVoice(audio, comment.getContent());
        comment.setAudio(path);
        commentMapper.insert(comment);
        return commentPraiseMapper.insert(new CommentPraise(0, comment.getPostId(), user.getId(), comment.getId()));
    }

    @Override
    public int insertBatch(List<Comment> list) {
        return commentMapper.insertBatch(list);
    }

    @Override
    public int update(String sessionKey, Comment comment) {
        User user = userTools.getUser(sessionKey);
        Comment commentTemp = commentMapper.getById(comment.getId());
        if (commentTemp == null) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, "评论不存在");
        }

        commentTemp.setContent(comment.getContent());
        Audio audio = audioService.getAudio(user.getId());
        String path = audioService.changeVoice(audio, comment.getContent());
        commentTemp.setAudio(path);
        return commentMapper.update(commentTemp);
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
    public int deleteByIds(String sessionKey, List<Long> list) {
        User user = userTools.getUser(sessionKey);

        for (Long i : list) {
            Comment comment = commentMapper.getById(i);
            Post post = postMapper.getById(comment.getPostId());
            if (!post.getUserId().equals(user.getId())) {
                throw new BaseException(ResultCode.PARAMETER_INVALID, "普通用户不能删除他人评论");
            }
            CommentPraise commentPraise = new CommentPraise();
            commentPraise.setCommentId(comment.getId());
            commentPraiseMapper.deleteByEntity(commentPraise);
        }

        return commentMapper.deleteByIds(list);
    }

    @Override
    public int countAll() {
        return commentMapper.countAll();
    }

    @Override
    public synchronized CommentPraise praise(Long postId, Long commentId) {
        CommentPraise tempCommentPraise = new CommentPraise();
        tempCommentPraise.setCommentId(commentId);
        tempCommentPraise.setPostId(postId);
        CommentPraise commentPraise = commentPraiseMapper.getByEntity(tempCommentPraise);
        commentPraise.setPraise(commentPraise.getPraise() + 1);
        commentPraiseMapper.update(commentPraise);
        return commentPraise;
    }

    @Override
    public int countByEntity(Comment comment) {
        return commentMapper.countByEntity(comment);
    }

}
