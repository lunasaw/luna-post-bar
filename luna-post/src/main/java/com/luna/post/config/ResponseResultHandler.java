package com.luna.post.config;

import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.ResultDTOUtils;
import com.luna.common.dto.constant.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author luna@mac
 * 2021年04月10日 10:37
 */
@RestControllerAdvice
public class ResponseResultHandler {

    @ExceptionHandler(value = Exception.class)
    ResultDTO<Void> handleException(Exception e, HttpServletRequest request) {
        return ResultDTOUtils.failure(ResultCode.ERROR_SYSTEM_EXCEPTION, e.getMessage());
    }
}