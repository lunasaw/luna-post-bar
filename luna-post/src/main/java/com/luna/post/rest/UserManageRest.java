package com.luna.post.rest;

import com.github.pagehelper.PageInfo;
import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.ResultDTOUtils;
import com.luna.common.dto.constant.ResultCode;
import com.luna.post.dto.ShowUserDTO;
import com.luna.post.entity.User;
import com.luna.post.service.UserService;
import com.luna.post.user.UserManager;
import com.luna.post.utils.CookieUtils;
import com.luna.post.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author luna
 * 2021/5/28
 */
@RestController
@RequestMapping("/user/api")
public class UserManageRest {

    @Autowired
    private UserManager userManager;

    @GetMapping("/showUserPageList/{page}/{size}")
    public ResultDTO<PageInfo<ShowUserDTO>> listPageByEntity(@PathVariable(value = "page") int page,
        @PathVariable(value = "size") int size, User user) {
        PageInfo<ShowUserDTO> pageInfo = userManager.listPageByEntity(page, size, user);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, pageInfo);
    }

    @PutMapping("/userManage/update")
    public ResultDTO<Boolean> update(@RequestBody ShowUserDTO showUserDTO) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, userManager.update(showUserDTO) == 1);
    }

    @PutMapping("/userManage/updateOwner")
    public ResultDTO<Boolean> update(HttpServletRequest request, @Validated @RequestBody ShowUserDTO showUserDTO) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            userManager.updateOwner(CookieUtils.getOneSessionKey(request), showUserDTO) == 1);
    }

    @GetMapping("/sysUserInfo")
    public ResultDTO<ShowUserDTO> sysUser(HttpServletRequest request) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            userManager.sysUserInfo(CookieUtils.getOneSessionKey(request)));
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/upload")
    public ResultDTO<?> uploadFile(HttpServletRequest request, MultipartFile file) {
        return ResultDTOUtils.success(userManager.uploadImg(CookieUtils.getOneSessionKey(request), file));
    }
}
