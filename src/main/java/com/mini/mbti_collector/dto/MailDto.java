package com.mini.mbti_collector.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

public class MailDto {

    @Data
    @NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동으로 생성합니다.
    @AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 자동으로 생성합니다.
    public static class Request {
        @NotBlank(message = "Email should not be blank.") // 문자열이 null이거나 빈 값(empty)이 아닌지 검사합니다.
        @Email(message = "Invalid email format.") // 문자열이 유효한 이메일 형식인지 검사합니다.
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Invalid email format.")
        private String email;
    }

    @Data
    @NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동으로 생성합니다.
    @AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 자동으로 생성합니다.
    public static class Response {
        private String authNum;
        private String message;
        private int status;
    }
}
