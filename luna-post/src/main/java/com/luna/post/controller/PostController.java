package com.luna.post.controller;

import com.github.pagehelper.PageInfo;
import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.constant.ResultCode;
import com.luna.post.dto.PostDTO;
import com.luna.post.entity.Post;
import com.luna.post.service.PostService;
import com.luna.post.utils.CookieUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-05-27 17:19:51
 */
@RestController
@RequestMapping("/post/api")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/get/{id}")
    public ResultDTO<Post> getById(@PathVariable(value = "id") Long id) {
        Post post = postService.getById(id);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, post);
    }

    @GetMapping("/get")
    public ResultDTO<Post> getByEntity(Post post) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, postService.getByEntity(post));
    }

    @GetMapping("/list")
    public ResultDTO<List<Post>> list(Post post) {
        List<Post> postList = postService.listByEntity(post);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, postList);
    }

    @GetMapping("/pageListByEntity/{page}/{size}")
    public ResultDTO<PageInfo<PostDTO>> listPageByEntity(HttpServletRequest httpServletRequest,
        @PathVariable(value = "page") int page,
        @PathVariable(value = "size") int size, Post post) {
        PageInfo<PostDTO> pageInfo =
            postService.listPageByEntity(CookieUtils.getOneSessionKey(httpServletRequest), page, size, post);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, pageInfo);
    }

    @GetMapping("/myPageListByEntity/{page}/{size}")
    public ResultDTO<PageInfo<PostDTO>> myPageListByEntity(HttpServletRequest httpServletRequest,
        @PathVariable(value = "page") int page, @PathVariable(value = "size") int size, Post post) {
        PageInfo<PostDTO> pageInfo =
            postService.myListPageByEntity(CookieUtils.getOneSessionKey(httpServletRequest), page, size, post);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, pageInfo);
    }

    @GetMapping("/pageList/{page}/{size}")
    public ResultDTO<PageInfo<Post>> listPage(@PathVariable(value = "page") int page,
        @PathVariable(value = "size") int size) {
        PageInfo<Post> pageInfo = postService.listPage(page, size);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, pageInfo);
    }

    @PostMapping("/insert")
    public ResultDTO<Post> insert(HttpServletRequest httpServletRequest, @RequestBody Post post) {
        postService.insert(CookieUtils.getOneSessionKey(httpServletRequest), post);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, post);
    }

    @PostMapping("/insertBatch")
    public ResultDTO<List<Post>> insert(@RequestBody List<Post> list) {
        postService.insertBatch(list);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, list);
    }

    @PutMapping("/update")
    public ResultDTO<Boolean> update(@RequestBody Post post) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, postService.update(post) == 1);
    }

    @PutMapping("/updateBatch")
    public ResultDTO<Boolean> update(@RequestBody List<Post> list) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            postService.updateBatch(list) == list.size());
    }

    @DeleteMapping("/delete/{id}")
    public ResultDTO<Boolean> deleteOne(@PathVariable(value = "id") Long id) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, postService.deleteById(id) == 1);
    }

    @DeleteMapping("/deleteByEntity")
    public ResultDTO<Boolean> deleteOne(@RequestBody Post post) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, postService.deleteByEntity(post) == 1);
    }

    @DeleteMapping("/delete")
    public ResultDTO<Integer> deleteBatch(@RequestBody List<Long> ids) {
        int result = 0;
        if (ids != null && ids.size() > 0) {
            result = postService.deleteByIds(ids);
        }
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, result);
    }

    @GetMapping("/account")
    public ResultDTO<Integer> getAccount() {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, postService.countAll());
    }

    @GetMapping("/accountByEntity")
    public ResultDTO<Integer> getAccountByEntity(Post post) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            postService.countByEntity(post));
    }
}
