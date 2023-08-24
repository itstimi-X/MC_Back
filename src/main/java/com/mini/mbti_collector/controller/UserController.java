package com.mini.mbti_collector.controller;

import com.mini.mbti_collector.dto.UserDto;
import com.mini.mbti_collector.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/api/users/check-nickname/{nickname}")
    public ResponseEntity<Boolean> isNicknameRegistered(@PathVariable String nickname) {
        return ResponseEntity.ok(userService.isNicknameRegistered(nickname));
    }

    @GetMapping("/api/users/check-email/{email}")
    public ResponseEntity<Boolean> isEmailRegistered(@PathVariable String email) {
        return ResponseEntity.ok(userService.isEmailRegistered(email));
    }

    @PostMapping("/api/users/sign-up")
    public ResponseEntity<?> signup(@Valid @RequestBody UserDto.SignUpRequest userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 유효성 검사에 실패한 경우, 오류 메시지 처리
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }

        try {
            // 회원가입 로직 처리
            userService.signUp(userDto);
        } catch (Exception e) {
            // 서비스 메서드에서 발생하는 에러 처리
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/api/users/login")
    public ResponseEntity<?> login(@RequestBody UserDto.loginRequest userDto) {
        try {
            UserDto.loginResponse response = userService.login(userDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
