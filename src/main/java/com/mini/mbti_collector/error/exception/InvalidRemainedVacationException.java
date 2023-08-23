package com.mini.mbti_collector.error.exception;

import com.mini.mbti_collector.error.ErrorCode;
import lombok.Getter;
@Getter
public class InvalidRemainedVacationException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;

    public InvalidRemainedVacationException(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
