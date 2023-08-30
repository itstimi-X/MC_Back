package com.mini.mbti_collector.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
        @NotNull(message = "ePercent 필드는 비어있을 수 없습니다.")
        @Min(value = 0, message = "ePercent 값은 0 이상이어야 합니다.")
        @Max(value = 100, message = "ePercent 값은 100 이하여야 합니다.")
        private int ePercent;

        @JsonProperty("n_percent")
        @NotNull(message = "nPercent 필드는 비어있을 수 없습니다.")
        @Min(value = 0, message = "nPercent 값은 0 이상이어야 합니다.")
        @Max(value = 100, message = "nPercent 값은 100 이하여야 합니다.")
        private int nPercent;

        @JsonProperty("t_percent")
        @NotNull(message = "tPercent 필드는 비어있을 수 없습니다.")
        @Min(value = 0, message = "tPercent 값은 0 이상이어야 합니다.")
        @Max(value = 100, message = "tPercent 값은 100 이하여야 합니다.")
        private int tPercent;

        @JsonProperty("j_percent")
        @NotNull(message = "jPercent 필드는 비어있을 수 없습니다.")
        @Min(value = 0, message = "jPercent 값은 0 이상이어야 합니다.")
        @Max(value = 100, message = "jPercent 값은 100 이하여야 합니다.")
        private int jPercent;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LatestResult {

        @JsonProperty("e_percent")
        private int ePercent;
        @JsonProperty("n_percent")
        private int nPercent;
        @JsonProperty("t_percent")
        private int tPercent;
        @JsonProperty("j_percent")
        private int jPercent;
        @JsonProperty("result_mbti")
        private String resultMbti;
        @JsonProperty("reg_date")
        private LocalDateTime regDate;

    }
}
