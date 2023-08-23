package com.mini.mbti_collector.error.exception;

import com.mini.mbti_collector.dto.ValidationErrorDto;
import com.mini.mbti_collector.error.ErrorCode;
import lombok.Getter;
@Getter
public class FieldValidationException extends RuntimeException {
    private ErrorCode errorCode;
    private ValidationErrorDto validationErrorDto;

    public FieldValidationException(ErrorCode errorCode, ValidationErrorDto validationErrorDto) {
        this.errorCode = errorCode;
        this.validationErrorDto = validationErrorDto;
    }
}
