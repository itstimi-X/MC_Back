package com.mini.mbti_collector.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

public class MailDto {

    @Data
    @NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동으로 생성합니다.
    @AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 자동으로 생성합니다.
    public static class Request {
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
