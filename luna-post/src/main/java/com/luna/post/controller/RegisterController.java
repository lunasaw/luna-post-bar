package com.luna.post.controller;

import com.github.pagehelper.PageInfo;
import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.constant.ResultCode;
import com.luna.post.entity.Register;
import com.luna.post.service.RegisterService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: luna
 * @CreateTime: 2021-05-27 17:19:07
 */
@RestController
@RequestMapping("/register/api")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/get/{id}")
    public ResultDTO<Register> getById(@PathVariable(value = "id") Long id) {
        Register register = registerService.getById(id);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, register);
    }

    @GetMapping("/get")
    public ResultDTO<Register> getByEntity(Register register) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, registerService.getByEntity(register));
    }

    @GetMapping("/list")
    public ResultDTO<List<Register>> list(Register register) {
        List<Register> registerList = registerService.listByEntity(register);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, registerList);
    }

    @GetMapping("/pageListByEntity/{page}/{size}")
    public ResultDTO<PageInfo<Register>> listPageByEntity(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size, Register register) {
        PageInfo<Register> pageInfo = registerService.listPageByEntity(page, size, register);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, pageInfo);
    }

    @GetMapping("/pageList/{page}/{size}")
    public ResultDTO<PageInfo<Register>> listPage(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
        PageInfo<Register> pageInfo = registerService.listPage(page, size);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, pageInfo);
    }

    @PostMapping("/insert")
    public ResultDTO<Register> insert(@RequestBody Register register) {
        registerService.insert(register);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, register);
    }

    @PostMapping("/insertBatch")
    public ResultDTO<List<Register>> insert(@RequestBody List<Register> list) {
        registerService.insertBatch(list);
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, list);
    }

    @PutMapping("/update")
    public ResultDTO<Boolean> update(@RequestBody Register register) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, registerService.update(register) == 1);
    }

    @PutMapping("/updateBatch")
    public ResultDTO<Boolean> update(@RequestBody List<Register> list) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, registerService.updateBatch(list) == list.size());
    }

    @DeleteMapping("/delete/{id}")
    public ResultDTO<Boolean> deleteOne(@PathVariable(value = "id") Long id) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, registerService.deleteById(id) == 1);
    }

    @DeleteMapping("/deleteByEntity")
    public ResultDTO<Boolean> deleteOne(@RequestBody Register register) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, registerService.deleteByEntity(register) == 1);
    }

    @DeleteMapping("/delete")
    public ResultDTO<Integer> deleteBatch(@RequestBody List<Long> ids) {
        int result = 0;
        if (ids != null && ids.size() > 0) {
            result = registerService.deleteByIds(ids);
        }
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, result);
    }

    @GetMapping("/account")
    public ResultDTO<Integer> getAccount() {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, registerService.countAll());
    }

    @GetMapping("/accountByEntity")
    public ResultDTO<Integer> getAccountByEntity(Register register) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
                registerService.countByEntity(register));
    }
}
