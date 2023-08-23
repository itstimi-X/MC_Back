package com.mini.mbti_collector.controller;

import com.mini.mbti_collector.dto.FieldErrorDto;
import com.mini.mbti_collector.dto.TokenDto;
import com.mini.mbti_collector.dto.UserDto;
import com.mini.mbti_collector.dto.ValidationErrorDto;
import com.mini.mbti_collector.service.UserService;
import jakarta.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;
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
    public ResponseEntity<?> signup(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
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

    /**
     * 로그인 시 access 토큰, refresh 토큰 모두 새로 만들어준다.
     * @param userDto
     * @return
     * @throws Exception
     */
    @PostMapping("/api/users/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserDto.loginRequest userDto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            // 유효성 검사에 실패한 경우, 오류 메시지 처리
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }

        try {
            // 로그인 로직 처리
            userService.login(userDto);
        } catch (Exception e) {
            // 서비스 메서드에서 발생하는 에러 처리
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("로그인 성공");
    }

//    /**
//     * access토큰 내부의 유저 정보를 확인한 후 access 토큰 새로 만들어준다.
//     *
//     * @param accessToken 엑세스 토큰
//     * @return
//     * @throws Exception
//     */
//    @PostMapping("/api/refresh")
//    public ResponseEntity<TokenDto> refresh(@RequestBody Map<String, String> accessToken) throws Exception {
//        return new ResponseEntity<>(userService.refreshToken(accessToken.get("accessToken")), HttpStatus.OK);
//    }
//
//    /**
//     * accessToken에 담긴 유저정보를 꺼내서 refresh token을 지워준다.
//     * @param accessToken 엑세스 토큰
//     * @return
//     */
//    @PostMapping("/api/logout")
//    public ResponseEntity logout(@RequestBody Map<String, String> accessToken) {
//        return new ResponseEntity(userService.logout(accessToken.get("accessToken")), HttpStatus.OK);
//    }

    private ValidationErrorDto handleBindingResult(BindingResult bindingResult) {

        return ValidationErrorDto
                .of(
                        bindingResult.
                                getFieldErrors().stream()
                                .map(error -> FieldErrorDto.of(error.getField(), error.getDefaultMessage()))
                                .collect(Collectors.toList()));
    }
}
