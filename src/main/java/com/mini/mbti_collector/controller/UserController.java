package com.mini.mbti_collector.controller;

import com.mini.mbti_collector.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

}
