package com.luna.post.controller;

import com.github.pagehelper.PageInfo;
import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.constant.ResultCode;
import com.luna.post.dto.CommentDTO;
import com.luna.post.entity.Comment;
import com.luna.post.service.CommentService;
import com.luna.post.utils.CookieUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-05-27 17:20:27
 */
@RestController
@RequestMapping("/comment/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/get/{id}")
    public ResultDTO<Comment> getById(@PathVariable(value = "id") Long id) {
        Comment comment = commentService.getById(id);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, comment);
    }

    @GetMapping("/get")
    public ResultDTO<Comment> getByEntity(Comment comment) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, commentService.getByEntity(comment));
    }

    @GetMapping("/list")
    public ResultDTO<List<CommentDTO>> list(Comment comment) {
        List<CommentDTO> commentList = commentService.listByEntity(comment);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, commentList);
    }

    @GetMapping("/pageListByEntity/{page}/{size}")
    public ResultDTO<PageInfo<Comment>> listPageByEntity(@PathVariable(value = "page") int page,
        @PathVariable(value = "size") int size, Comment comment) {
        PageInfo<Comment> pageInfo = commentService.listPageByEntity(page, size, comment);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, pageInfo);
    }

    @GetMapping("/pageList/{page}/{size}")
    public ResultDTO<PageInfo<Comment>> listPage(@PathVariable(value = "page") int page,
        @PathVariable(value = "size") int size) {
        PageInfo<Comment> pageInfo = commentService.listPage(page, size);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, pageInfo);
    }

    @PostMapping("/insert")
    public ResultDTO<Comment> insert(HttpServletRequest httpServletRequest, @RequestBody Comment comment) {
        commentService.insert(CookieUtils.getOneSessionKey(httpServletRequest), comment);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, comment);
    }

    @PostMapping("/insertBatch")
    public ResultDTO<List<Comment>> insert(@RequestBody List<Comment> list) {
        commentService.insertBatch(list);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, list);
    }

    @PutMapping("/update")
    public ResultDTO<Boolean> update(@RequestBody Comment comment) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, commentService.update(comment) == 1);
    }

    @PutMapping("/updateBatch")
    public ResultDTO<Boolean> update(@RequestBody List<Comment> list) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            commentService.updateBatch(list) == list.size());
    }

    @DeleteMapping("/delete/{id}")
    public ResultDTO<Boolean> deleteOne(@PathVariable(value = "id") Long id) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, commentService.deleteById(id) == 1);
    }

    @DeleteMapping("/deleteByEntity")
    public ResultDTO<Boolean> deleteOne(@RequestBody Comment comment) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            commentService.deleteByEntity(comment) == 1);
    }

    @DeleteMapping("/delete")
    public ResultDTO<Integer> deleteBatch(@RequestBody List<Long> ids) {
        int result = 0;
        if (ids != null && ids.size() > 0) {
            result = commentService.deleteByIds(ids);
        }
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, result);
    }

    @GetMapping("/account")
    public ResultDTO<Integer> getAccount() {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, commentService.countAll());
    }

    @GetMapping("/accountByEntity")
    public ResultDTO<Integer> getAccountByEntity(Comment comment) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            commentService.countByEntity(comment));
    }
}
