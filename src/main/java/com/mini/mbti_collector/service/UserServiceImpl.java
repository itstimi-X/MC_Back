package com.mini.mbti_collector.service;

import com.mini.mbti_collector.Security.JwtProvider;
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
    private final JwtProvider jwtProvider;
    @Override
    public boolean isNicknameRegistered(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void signUp(UserDto.SignUpRequest userDto) throws Exception{
        // 닉네임 중복 검사
        if (isNicknameRegistered(userDto.getNickname())) {
            throw new Exception("이미 사용 중인 닉네임입니다.");
        }
        // 이메일 중복 검사
        if (isEmailRegistered(userDto.getEmail())) {
            throw new Exception("이미 사용 중인 이메일입니다.");
        }
        // 비밀번호 해싱
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        // 사용자 정보 저장
        User user = User.of(userDto.getNickname(), userDto.getEmail(), encodedPassword);
        userRepository.save(user);
    }

    @Override
    public UserDto.loginResponse login(UserDto.loginRequest userDto) throws Exception {
        User user = userRepository.findByEmail(userDto.getEmail()).orElseThrow(() ->
                new Exception("유저네임 또는 비밀번호가 잘못되었습니다."));

        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new Exception("유저네임 또는 비밀번호가 잘못되었습니다.");
        }

        String accessToken = jwtProvider.createAccessToken(user.getEmail(), user.getNickname());
        return new UserDto.loginResponse(accessToken);
    }
}
