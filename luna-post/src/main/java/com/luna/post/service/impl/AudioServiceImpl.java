package com.luna.post.service.impl;

import com.alibaba.fastjson.JSON;
import com.luna.common.dto.constant.ResultCode;
import com.luna.post.config.LoginInterceptor;
import com.luna.post.dto.AudioDTO;
import com.luna.post.entity.User;
import com.luna.post.entity.UserException;
import com.luna.post.mapper.AudioMapper;
import com.luna.post.mapper.UserMapper;
import com.luna.post.service.AudioService;
import com.luna.post.entity.Audio;

import com.luna.redis.util.RedisHashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-05-27 17:20:43
 */
@Service
public class AudioServiceImpl implements AudioService {

    @Resource
    private AudioMapper   audioMapper;

    @Resource
    private UserMapper    userMapper;

    @Resource
    private RedisHashUtil redisHashUtil;

    @Override
    public Audio getById(Long id) {
        return audioMapper.getById(id);
    }

    @Override
    public Audio getByEntity(String sessionKey, Audio audio) {
        if (sessionKey == null) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "用户不存在");
        }

        User user = (User)redisHashUtil.get(LoginInterceptor.sessionKey + ":" + sessionKey, sessionKey);

        if (audio == null) {
            audio = new Audio(user.getId());
        }
        return audioMapper.getByEntity(audio);
    }

    @Override
    public List<Audio> listByEntity(Audio audio) {
        return audioMapper.listByEntity(audio);
    }

    @Override
    public PageInfo<Audio> listPageByEntity(int page, int pageSize, Audio audio) {
        PageHelper.startPage(page, pageSize);
        List<Audio> list = audioMapper.listByEntity(audio);
        return new PageInfo<Audio>(list);
    }

    @Override
    public PageInfo<Audio> listPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Audio> list = audioMapper.listByEntity(new Audio());
        return new PageInfo<Audio>(list);
    }

    @Override
    public List<Audio> listByIds(List<Long> ids) {
        return audioMapper.listByIds(ids);
    }

    @Override
    public int insert(String sessionKey, AudioDTO audioDTO) {
        if (sessionKey == null) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "用户不存在");
        }

        User user = (User)redisHashUtil.get(LoginInterceptor.sessionKey + ":" + sessionKey, sessionKey);
        Audio audio = new Audio();
        audio.setUserId(user.getId());
        audio.setAudioSpd(audioDTO.getAudioSpd());
        audio.setAudioPit(audioDTO.getAudioPit());
        audio.setAudioVol(audioDTO.getAudioVol());
        audio.setAudioVoiPer(audioDTO.getAudioVoiPer());
        return audioMapper.insert(audio);
    }

    @Override
    public int insertBatch(List<Audio> list) {
        return audioMapper.insertBatch(list);
    }

    @Override
    public int update(String sessionKey, AudioDTO audioDTO) {
        if (sessionKey == null) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "用户不存在");
        }

        User user = (User)redisHashUtil.get(LoginInterceptor.sessionKey + ":" + sessionKey, sessionKey);
        Audio byEntity = audioMapper.getByEntity(new Audio(user.getId()));

        if (byEntity == null) {
            return insert(sessionKey, audioDTO);
        } else {
            byEntity.setAudioSpd(audioDTO.getAudioSpd());
            byEntity.setAudioPit(audioDTO.getAudioPit());
            byEntity.setAudioVol(audioDTO.getAudioVol());
            byEntity.setAudioVoiPer(audioDTO.getAudioVoiPer());
        }
        return audioMapper.update(byEntity);
    }

    @Override
    public int updateBatch(List<Audio> list) {
        return audioMapper.updateBatch(list);
    }

    @Override
    public int deleteById(Long id) {
        return audioMapper.deleteById(id);
    }

    @Override
    public int deleteByEntity(Audio audio) {
        return audioMapper.deleteByEntity(audio);
    }

    @Override
    public int deleteByIds(List<Long> list) {
        return audioMapper.deleteByIds(list);
    }

    @Override
    public int countAll() {
        return audioMapper.countAll();
    }

    @Override
    public int countByEntity(Audio audio) {
        return audioMapper.countByEntity(audio);
    }

}
