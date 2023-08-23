package com.mini.mbti_collector.dto;

public record FieldErrorDto (String field, String message) {

    public static FieldErrorDto of(String field, String message) {
        return new FieldErrorDto(field, message);
    }
}

