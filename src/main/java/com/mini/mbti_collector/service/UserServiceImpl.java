package com.mini.mbti_collector.service;

import com.mini.mbti_collector.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public boolean isNicknameRegistered(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return userRepository.existsByEmail(email);
    }
}
