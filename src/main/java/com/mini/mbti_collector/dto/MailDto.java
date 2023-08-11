package com.mini.mbti_collector.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
        @Size(min = 1, max = 255, message = "Email length should be between 1 and 255 characters.") // DB 스키마에 따라 email 필드의 최대 길이가 255자로 정의되어 있으므로, 이에 대한 검증을 DTO에서도 적용. 이렇게 DTO에서 길이 제한을 검증하면, 사용자가 255자를 초과하는 긴 이메일 주소를 입력하려고 할 때 요청 단계에서 오류를 반환하여 DB에 저장되기 전에 문제를 잡아낼 수 있음.
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
