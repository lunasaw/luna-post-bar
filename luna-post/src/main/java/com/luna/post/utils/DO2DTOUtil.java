package com.luna.post.utils;

import com.alibaba.fastjson.JSON;
import com.luna.baidu.req.VoiceSynthesisReq;
import com.luna.common.date.DateUtil;
import com.luna.common.net.HttpUtils;
import com.luna.common.os.SystemInfoUtil;
import com.luna.post.dto.CommentDTO;
import com.luna.post.dto.PostDTO;
import com.luna.post.dto.ShowUserDTO;
import com.luna.post.entity.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author luna
 * 2021/5/28
 */
public class DO2DTOUtil {

    public static ShowUserDTO user2ShowUserDTO(User user, Register register) {
        if (user == null) {
            return null;
        }
        ShowUserDTO showUserDTO = new ShowUserDTO();
        showUserDTO.setId(user.getId());
        showUserDTO.setName(user.getName());
        showUserDTO.setPassword(user.getPassword());
        showUserDTO.setLoginTime(
            user.getLoginTime() != null ? DateUtil.parseDateToStr(DateUtil.YYYY_MM_DD, user.getLoginTime()) : "");
        showUserDTO.setAdmin("1".equals(user.getAdmin()) ? "管理员" : "客户");
        showUserDTO.setCreateTime(DateUtil.parseDateToStr(DateUtil.YYYY_MM_DD, user.getCreateTime()));
        if (register == null) {
            return showUserDTO;
        }
        showUserDTO.setSex("1".equals(register.getSex()) ? "男" : "女");
        showUserDTO.setAge(register.getAge());
        showUserDTO.setEmail(register.getEmail());
        showUserDTO.setPhoto(register.getPhoto());
        return showUserDTO;
    }

    public static PostDTO postDO2PostDTO(Post post, String username, Date lastComment, Integer readCount) {
        if (post == null) {
            return null;
        }

        PostDTO postDTO = new PostDTO();
        postDTO.setUsername(username);
        postDTO.setLastComment(
            lastComment != null ? DateUtil.parseDateToStr(DateUtil.YYYY_MM_DD_HH_MM_SS, post.getCreateTime()) : "");
        postDTO.setPostCommentSize(readCount != null ? readCount : 0);
        postDTO.setId(post.getId());
        postDTO.setUserId(post.getUserId());
        postDTO.setPostTitle(post.getPostTitle());
        postDTO.setPostText(post.getPostText());
        postDTO.setPostPageViews(post.getPostPageViews());
        postDTO.setPostAudio(post.getPostAudio());
        postDTO.setCreateTime(
            post.getCreateTime() != null ? DateUtil.parseDateToStr(DateUtil.YYYY_MM_DD_HH_MM_SS, post.getCreateTime())
                : "");
        postDTO.setModifiedTime(
            post.getModifiedTime() != null
                ? DateUtil.parseDateToStr(DateUtil.YYYY_MM_DD_HH_MM_SS, post.getModifiedTime()) : "");
        postDTO.setVersion(post.getVersion());
        return postDTO;
    }

    public static VoiceSynthesisReq audio2VoiceSynthesisReq(String cuid, String tex, String tok, Audio audio) {
        VoiceSynthesisReq voiceSynthesisReq = new VoiceSynthesisReq(cuid, tex, tok);
        voiceSynthesisReq.setPer(String.valueOf(audio.getAudioVoiPer()));
        voiceSynthesisReq.setSpd(String.valueOf(audio.getAudioSpd()));
        voiceSynthesisReq.setPit(String.valueOf(audio.getAudioPit()));
        voiceSynthesisReq.setVol(String.valueOf(audio.getAudioVol()));
        return voiceSynthesisReq;
    }

    public static CommentDTO comment2CommentDTO(Comment comment, String username, Date userTime, String photo) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setUsername(username);
        commentDTO.setUserTime(userTime != null
            ? DateUtil.parseDateToStr(DateUtil.YYYY_MM_DD_HH_MM_SS, userTime) : "");
        commentDTO.setPhoto(photo);
        commentDTO.setId(comment.getId());
        commentDTO.setPostId(comment.getPostId());
        commentDTO.setUserId(comment.getUserId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setAudio(comment.getAudio());
        commentDTO.setCreateTime(comment.getCreateTime() != null
            ? DateUtil.parseDateToStr(DateUtil.YYYY_MM_DD_HH_MM_SS, comment.getCreateTime()) : "");
        commentDTO.setModifiedTime(comment.getModifiedTime() != null
            ? DateUtil.parseDateToStr(DateUtil.YYYY_MM_DD_HH_MM_SS, comment.getModifiedTime()) : "");
        commentDTO.setVersion(comment.getVersion());
        return commentDTO;

    }
}
