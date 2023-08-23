package com.mini.mbti_collector.error.exception;

import com.mini.mbti_collector.error.ErrorCode;
import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    private ErrorCode errorCode;

    public BadRequestException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
