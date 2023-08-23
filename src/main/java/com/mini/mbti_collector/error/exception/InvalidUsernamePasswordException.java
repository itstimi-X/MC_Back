package com.mini.mbti_collector.error.exception;


import com.mini.mbti_collector.error.ErrorCode;

public class InvalidUsernamePasswordException extends RuntimeException {
    private ErrorCode errorCode;

    public InvalidUsernamePasswordException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
