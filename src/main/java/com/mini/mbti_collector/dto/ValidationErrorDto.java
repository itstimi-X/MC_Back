package com.mini.mbti_collector.dto;

import java.util.List;

public record ValidationErrorDto (List<FieldErrorDto> fieldErrors) {
    public static ValidationErrorDto of(List<FieldErrorDto> fieldErrors) {
        return new ValidationErrorDto(fieldErrors);
    }
}
