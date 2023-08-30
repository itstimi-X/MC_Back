package com.mini.mbti_collector.controller;

import com.mini.mbti_collector.dto.MbtiDto;
import com.mini.mbti_collector.exception.CustomAuthenticationException;
import com.mini.mbti_collector.service.MbtiService;
import com.mini.mbti_collector.service.MbtiServiceImpl;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MbtiController {

    private final MbtiService mbtiService;
    private static final Logger logger = LoggerFactory.getLogger(MbtiServiceImpl.class);
    @PostMapping("/api/mbti/save")
    public ResponseEntity<?> saveMbtiResult(@RequestHeader("Authorization") String authorizationHeader, @RequestBody @Valid MbtiDto.Request request) {
        logger.info("ePercent: {}, nPercent: {}, tPercent: {}, jPercent: {}",
                request.getEPercent(), request.getNPercent(), request.getTPercent(), request.getJPercent());
        try {
            mbtiService.saveMbtiResult(authorizationHeader, request);
            return new ResponseEntity<>(Map.of("success", true, "message", "MBTI 결과가 성공적으로 저장되었습니다."), HttpStatus.OK);
        } catch (CustomAuthenticationException e) {
            return new ResponseEntity<>(Map.of("success", false, "message", e.getMessage()), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("success", false, "message", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


}
