package com.luna.post.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 音频配置表(Audio)实体类
 *
 * @author luna
 * @since 2021-05-27 17:20:43
 */
public class AudioDTO implements Serializable {
    private static final long serialVersionUID = -19124223948855100L;
    private Long              id;
    /**
     * 用户Id
     */
    private Long              userId;
    /**
     * 语速，取值0-15，默认为5中语速
     */
    @Min(0)
    @Max(15)
    private Integer           audioSpd;
    /**
     * 音调，取值0-15，默认为5中语调
     */
    @Min(0)
    @Max(15)
    private Integer           audioPit;
    /**
     * 音量，取值0-15，默认为5中音量（取值为0时为音量最小值，并非为无声）
     */
    @Min(0)
    @Max(15)
    private Integer           audioVol;
    /**
     * 度小宇=1，度小美=0，度逍遥（基础）=3，度丫丫=4
     */
    @Min(0)
    @Max(4)
    private Integer           audioVoiPer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAudioSpd() {
        return audioSpd;
    }

    public void setAudioSpd(Integer audioSpd) {
        this.audioSpd = audioSpd;
    }

    public Integer getAudioPit() {
        return audioPit;
    }

    public void setAudioPit(Integer audioPit) {
        this.audioPit = audioPit;
    }

    public Integer getAudioVol() {
        return audioVol;
    }

    public void setAudioVol(Integer audioVol) {
        this.audioVol = audioVol;
    }

    public Integer getAudioVoiPer() {
        return audioVoiPer;
    }

    public void setAudioVoiPer(Integer audioVoiPer) {
        this.audioVoiPer = audioVoiPer;
    }

}
