package com.mini.mbti_collector.service;

import com.mini.mbti_collector.dto.UserDto;

public interface UserService {
    boolean isNicknameRegistered(String nickname);

    boolean isEmailRegistered(String email);

    void signUp(UserDto userDto) throws Exception;
}
