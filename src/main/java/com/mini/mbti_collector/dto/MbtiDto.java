package com.mini.mbti_collector.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
        @JsonProperty("e_percent")
        private int ePercent;
        @JsonProperty("n_percent")
        private int nPercent;
        @JsonProperty("t_percent")
        private int tPercent;
        @JsonProperty("j_percent")
        private int jPercent;
//        private String resultMbti;
    }
}
