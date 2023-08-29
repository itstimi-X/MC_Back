package com.mini.mbti_collector.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MbtiDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private int ePercent;
        private int nPercent;
        private int tPercent;
        private int jPercent;
//        private String resultMbti;
    }
}
