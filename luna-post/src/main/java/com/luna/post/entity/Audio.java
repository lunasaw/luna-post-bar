package com.luna.post.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 音频配置表(Audio)实体类
 *
 * @author luna
 * @since 2021-05-27 17:20:43
 */
public class Audio implements Serializable {
    private static final long serialVersionUID = -19124223948855100L;
    private Long id;
    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 0-9
     */
    private Integer audioSpd;
    /**
     * 0-9
     */
    private Integer audioPit;
    /**
     * 0-15
     */
    private Integer audioVol;
    /**
     * 0-4
     */
    private Integer audioVoiPer;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifiedTime;
    /**
     * 锁
     */
    private Long version;


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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
