package com.luna.post.controller;

import com.github.pagehelper.PageInfo;
import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.constant.ResultCode;
import com.luna.post.entity.Audio;
import com.luna.post.service.AudioService;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-05-27 17:20:43
 */
@RestController
@RequestMapping("/audio/api")
public class AudioController {

    @Autowired
    private AudioService audioService;

    @GetMapping("/get/{id}")
    public ResultDTO<Audio> getById(@PathVariable(value = "id") Long id) {
        Audio audio = audioService.getById(id);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, audio);
    }

    @GetMapping("/get")
    public ResultDTO<Audio> getByEntity(Audio audio) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, audioService.getByEntity(audio));
    }

    @GetMapping("/list")
    public ResultDTO<List<Audio>> list(Audio audio) {
        List<Audio> audioList = audioService.listByEntity(audio);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, audioList);
    }

    @GetMapping("/pageListByEntity/{page}/{size}")
    public ResultDTO<PageInfo<Audio>> listPageByEntity(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size, Audio audio) {
        PageInfo<Audio> pageInfo = audioService.listPageByEntity(page, size, audio);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, pageInfo);
    }

    @GetMapping("/pageList/{page}/{size}")
    public ResultDTO<PageInfo<Audio>> listPage(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
        PageInfo<Audio> pageInfo = audioService.listPage(page, size);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, pageInfo);
    }

    @PostMapping("/insert")
    public ResultDTO<Audio> insert(@RequestBody Audio audio) {
        audioService.insert(audio);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, audio);
    }

    @PostMapping("/insertBatch")
    public ResultDTO<List<Audio>> insert(@RequestBody List<Audio> list) {
        audioService.insertBatch(list);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, list);
    }

    @PutMapping("/update")
    public ResultDTO<Boolean> update(@RequestBody Audio audio) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, audioService.update(audio) == 1);
    }

    @PutMapping("/updateBatch")
    public ResultDTO<Boolean> update(@RequestBody List<Audio> list) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, audioService.updateBatch(list) == list.size());
    }

    @DeleteMapping("/delete/{id}")
    public ResultDTO<Boolean> deleteOne(@PathVariable(value = "id") Long id) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, audioService.deleteById(id) == 1);
    }

    @DeleteMapping("/deleteByEntity")
    public ResultDTO<Boolean> deleteOne(@RequestBody Audio audio) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, audioService.deleteByEntity(audio) == 1);
    }

    @DeleteMapping("/delete")
    public ResultDTO<Integer> deleteBatch(@RequestBody List<Long> ids) {
        int result = 0;
        if (ids != null && ids.size() > 0) {
            result = audioService.deleteByIds(ids);
        }
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, result);
    }

    @GetMapping("/account")
    public ResultDTO<Integer> getAccount() {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, audioService.countAll());
    }

    @GetMapping("/accountByEntity")
    public ResultDTO<Integer> getAccountByEntity(Audio audio) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
                audioService.countByEntity(audio));
    }
}
