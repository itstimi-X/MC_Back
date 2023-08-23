package com.mini.mbti_collector.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class UserDto {




    @Getter
    @Setter
    @NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동으로 생성합니다.
    @AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 자동으로 생성합니다.
    public static class signUpRequest {
        @NotBlank(message = "닉네임을 입력해주세요.")
        @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
        private String nickname;

        @NotBlank(message = "이메일 주소를 입력해주세요.")
        @Email(message = "올바른 이메일 주소를 입력해주세요.")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Invalid email format.")
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
        private String password;
    }

    @Getter
    @Setter
    @NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동으로 생성합니다.
    @AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 자동으로 생성합니다.
    public static class loginRequest {

            @NotBlank(message = "이메일 주소를 입력해주세요.")
            @Email(message = "올바른 이메일 주소를 입력해주세요.")
            @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Invalid email format.")
            private String email;

            @NotBlank(message = "비밀번호를 입력해주세요.")
            @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
            private String password;
    }


    @Getter
    @Setter
    @NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동으로 생성합니다.
    @AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 자동으로 생성합니다.
    public static class loginResponse {
        private String accessToken;
    }

    
}