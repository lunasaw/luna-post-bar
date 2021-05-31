package com.luna.post.controller;

import com.github.pagehelper.PageInfo;
import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.ResultDTOUtils;
import com.luna.post.entity.PostPraise;
import com.luna.post.service.PostPraiseService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-05-31 16:34:55
 */
@RestController
@RequestMapping("/postPraise/api")
public class PostPraiseController {

    @Autowired
    private PostPraiseService postPraiseService;

    @GetMapping("/get/{id}")
    public ResultDTO<PostPraise> getById(@PathVariable(value = "id") Long id) {
        PostPraise postPraise = postPraiseService.getById(id);
        return ResultDTOUtils.success(postPraise);
    }

    @GetMapping("/get")
    public ResultDTO<PostPraise> getByEntity(PostPraise postPraise) {
        return ResultDTOUtils.success(postPraiseService.getByEntity(postPraise));
    }

    @GetMapping("/list")
    public ResultDTO<List<PostPraise>> list(PostPraise postPraise) {
        List<PostPraise> postPraiseList = postPraiseService.listByEntity(postPraise);
        return ResultDTOUtils.success(postPraiseList);
    }

    @GetMapping("/pageListByEntity/{page}/{size}")
    public ResultDTO<PageInfo<PostPraise>> listPageByEntity(@PathVariable(value = "page") int page,
        @PathVariable(value = "size") int size, PostPraise postPraise) {
        PageInfo<PostPraise> pageInfo = postPraiseService.listPageByEntity(page, size, postPraise);
        return ResultDTOUtils.success(pageInfo);
    }

    @GetMapping("/pageList/{page}/{size}")
    public ResultDTO<PageInfo<PostPraise>> listPage(@PathVariable(value = "page") int page,
        @PathVariable(value = "size") int size) {
        PageInfo<PostPraise> pageInfo = postPraiseService.listPage(page, size);
        return ResultDTOUtils.success(pageInfo);
    }

    @PostMapping("/insert")
    public ResultDTO<PostPraise> insert(@RequestBody PostPraise postPraise) {
        postPraiseService.insert(postPraise);
        return ResultDTOUtils.success(postPraise);
    }

    @PostMapping("/insertBatch")
    public ResultDTO<List<PostPraise>> insert(@RequestBody List<PostPraise> list) {
        postPraiseService.insertBatch(list);
        return ResultDTOUtils.success(list);
    }

    @PutMapping("/update")
    public ResultDTO<Boolean> update(@RequestBody PostPraise postPraise) {
        return ResultDTOUtils.success(postPraiseService.update(postPraise) == 1);
    }

    @PutMapping("/updateBatch")
    public ResultDTO<Boolean> update(@RequestBody List<PostPraise> list) {
        return ResultDTOUtils.success(postPraiseService.updateBatch(list) == list.size());
    }

    @DeleteMapping("/delete/{id}")
    public ResultDTO<Boolean> deleteOne(@PathVariable(value = "id") Long id) {
        return ResultDTOUtils.success(postPraiseService.deleteById(id) == 1);
    }

    @DeleteMapping("/deleteByEntity")
    public ResultDTO<Boolean> deleteOne(@RequestBody PostPraise postPraise) {
        return ResultDTOUtils.success(postPraiseService.deleteByEntity(postPraise) == 1);
    }

    @DeleteMapping("/delete")
    public ResultDTO<Integer> deleteBatch(@RequestBody List<Long> ids) {
        int result = 0;
        if (ids != null && ids.size() > 0) {
            result = postPraiseService.deleteByIds(ids);
        }
        return ResultDTOUtils.success(result);
    }

    @GetMapping("/account")
    public ResultDTO<Integer> getAccount() {
        return ResultDTOUtils.success(postPraiseService.countAll());
    }

    @GetMapping("/accountByEntity")
    public ResultDTO<Integer> getAccountByEntity(PostPraise postPraise) {
        return ResultDTOUtils.success(postPraiseService.countByEntity(postPraise));
    }
}
