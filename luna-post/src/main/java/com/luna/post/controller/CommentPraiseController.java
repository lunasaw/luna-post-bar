package com.luna.post.controller;

import com.luna.post.entity.CommentPraise;
import com.luna.post.service.CommentPraiseService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-05-28 22:17:26
 */
@RestController
@RequestMapping("/commentPraise/api")
public class CommentPraiseController {

    @Autowired
    private CommentPraiseService commentPraiseService;

    @GetMapping("/get/{id}")
    public ResultDTO<CommentPraise> getById(@PathVariable(value = "id") Long id) {
        CommentPraise commentPraise = commentPraiseService.getById(id);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            commentPraise);
    }

    @GetMapping("/get")
    public ResultDTO<CommentPraise> getByEntity(CommentPraise commentPraise) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            commentPraiseService.getByEntity(commentPraise));
    }

    @GetMapping("/list")
    public ResultDTO<List<CommentPraise>> list(CommentPraise commentPraise) {
        List<CommentPraise> commentPraiseList = commentPraiseService.listByEntity(commentPraise);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, commentPraiseList);
    }

    @GetMapping("/pageListByEntity/{page}/{size}")
    public ResultDTO<PageInfo<CommentPraise>> listPageByEntity(@PathVariable(value = "page") int page,
        @PathVariable(value = "size") int size, CommentPraise commentPraise) {
        PageInfo<CommentPraise> pageInfo = commentPraiseService.listPageByEntity(page, size, commentPraise);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, pageInfo);
    }

    @GetMapping("/pageList/{page}/{size}")
    public ResultDTO<PageInfo<CommentPraise>> listPage(@PathVariable(value = "page") int page,
        @PathVariable(value = "size") int size) {
        PageInfo<CommentPraise> pageInfo = commentPraiseService.listPage(page, size);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, pageInfo);
    }

    @PostMapping("/insert")
    public ResultDTO<CommentPraise> insert(@RequestBody CommentPraise commentPraise) {
        commentPraiseService.insert(commentPraise);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, commentPraise);
    }

    @PostMapping("/insertBatch")
    public ResultDTO<List<CommentPraise>> insert(@RequestBody List<CommentPraise> list) {
        commentPraiseService.insertBatch(list);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, list);
    }

    @PutMapping("/update")
    public ResultDTO<Boolean> update(@RequestBody CommentPraise commentPraise) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            commentPraiseService.update(commentPraise) == 1);
    }

    @PutMapping("/updateBatch")
    public ResultDTO<Boolean> update(@RequestBody List<CommentPraise> list) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            commentPraiseService.updateBatch(list) == list.size());
    }

    @DeleteMapping("/delete/{id}")
    public ResultDTO<Boolean> deleteOne(@PathVariable(value = "id") Long id) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            commentPraiseService.deleteById(id) == 1);
    }

    @DeleteMapping("/deleteByEntity")
    public ResultDTO<Boolean> deleteOne(@RequestBody CommentPraise commentPraise) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            commentPraiseService.deleteByEntity(commentPraise) == 1);
    }

    @DeleteMapping("/delete")
    public ResultDTO<Integer> deleteBatch(@RequestBody List<Long> ids) {
        int result = 0;
        if (ids != null && ids.size() > 0) {
            result = commentPraiseService.deleteByIds(ids);
        }
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, result);
    }

    @GetMapping("/account")
    public ResultDTO<Integer> getAccount() {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, commentPraiseService.countAll());
    }

    @GetMapping("/accountByEntity")
    public ResultDTO<Integer> getAccountByEntity(CommentPraise commentPraise) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            commentPraiseService.countByEntity(commentPraise));
    }
}
