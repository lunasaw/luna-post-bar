package com.luna.post.entity;

import com.luna.common.exception.BaseException;

/**
 * @author luna
 */
public class UserException extends BaseException {

    public UserException(int code, String message) {
        super(code, message);
    }
}