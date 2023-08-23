package com.mini.mbti_collector.service;

import com.mini.mbti_collector.dto.UserDto;

public interface UserService {
    boolean isNicknameRegistered(String nickname);

    boolean isEmailRegistered(String email);

    void signUp(UserDto userDto) throws Exception;
    void signUp(UserDto.signUpRequest userDto) throws Exception;
    UserDto.loginResponse login(UserDto.loginRequest userDto) throws Exception;
}
