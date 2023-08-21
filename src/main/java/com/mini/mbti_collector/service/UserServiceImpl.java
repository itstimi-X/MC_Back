package com.mini.mbti_collector.service;

import com.mini.mbti_collector.domain.User;
import com.mini.mbti_collector.dto.UserDto;
import com.mini.mbti_collector.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean isNicknameRegistered(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void signUp(UserDto userDto) {
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        User user = User.of(userDto.getNickname(), userDto.getEmail(), encodedPassword);
        userRepository.save(user);
    }

}
