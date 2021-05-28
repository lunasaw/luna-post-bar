package com.luna.post.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import com.luna.baidu.api.BaiduVoiceApi;
import com.luna.baidu.config.BaiduKeyGenerate;
import com.luna.baidu.config.BaiduProperties;
import com.luna.baidu.req.VoiceSynthesisReq;
import com.luna.common.date.DateUtil;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.file.FileTools;
import com.luna.common.os.SystemInfoUtil;
import com.luna.common.text.RandomStrUtil;
import com.luna.post.config.LoginInterceptor;
import com.luna.post.dto.PostDTO;
import com.luna.post.entity.*;
import com.luna.post.mapper.AudioMapper;
import com.luna.post.mapper.CommentMapper;
import com.luna.post.mapper.PostMapper;
import com.luna.post.mapper.UserMapper;
import com.luna.post.service.PostService;
import com.luna.post.user.UserManager;
import com.luna.post.utils.DO2DTOUtil;
import com.luna.post.utils.FileUploadUtils;
import com.luna.redis.util.RedisHashUtil;
import org.apache.http.client.utils.URLEncodedUtils;
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
    private PostMapper      postMapper;

    @Resource
    private CommentMapper   commentMapper;

    @Resource
    private UserMapper      userMapper;

    @Resource
    private AudioMapper     audioMapper;

    @Resource
    private UserManager     userManager;

    @Resource
    private RedisHashUtil   redisHashUtil;

    @Resource
    private BaiduProperties baiduProperties;

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
    public PageInfo<PostDTO> listPageByEntity(String sessionKey, int page, int pageSize, Post post) {
        if (sessionKey == null) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "用户不存在");
        }
        User user = (User)redisHashUtil.get(LoginInterceptor.sessionKey + ":" + sessionKey, sessionKey);

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
        if (sessionKey == null) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "用户不存在");
        }
        User user = (User)redisHashUtil.get(LoginInterceptor.sessionKey + ":" + sessionKey, sessionKey);
        post.setUserId(user.getId());

        // TODO 这里需要讲内容转为语音
        Audio byEntity = audioMapper.getByEntity(new Audio(user.getId()));

        if (byEntity == null) {
            byEntity = new Audio();
            byEntity.setUserId(user.getId());
            byEntity.setAudioSpd(5);
            byEntity.setAudioPit(5);
            byEntity.setAudioVol(5);
            byEntity.setAudioVoiPer(0);
            audioMapper.insert(byEntity);
        }

        VoiceSynthesisReq voiceSynthesisReq = DO2DTOUtil.audio2VoiceSynthesisReq(SystemInfoUtil.getMac(),
            post.getPostText(), baiduProperties.getBaiduKey(), byEntity);

        if (voiceSynthesisReq != null) {
            try {
                byte[] bytes = BaiduVoiceApi.voiceSynthesis(voiceSynthesisReq);
                String fileName = FileUploadUtils.defaultBaseDir + "/" + DateUtil.datePath() + "/"
                    + RandomStrUtil.generateNonceStrWithUUID() + ".mp3";
                FileTools.createDirectory(fileName);
                FileTools.write(bytes, fileName);
                post.setPostAudio(userManager.getPath() + "/" + fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
    public PageInfo<PostDTO> myListPageByEntity(String sessionKey, int page, int size, Post post) {
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
