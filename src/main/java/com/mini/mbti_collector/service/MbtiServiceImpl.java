package com.mini.mbti_collector.service;

import com.mini.mbti_collector.Security.JwtProvider;
import com.mini.mbti_collector.domain.MbtiResult;
import com.mini.mbti_collector.domain.User;
import com.mini.mbti_collector.dto.MbtiDto;
import com.mini.mbti_collector.exception.CustomAuthenticationException;
import com.mini.mbti_collector.repository.MbtiResultRepository;
import com.mini.mbti_collector.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MbtiServiceImpl implements MbtiService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final MbtiResultRepository mbtiResultRepository;

    @Override
    public void saveMbtiResult(String authorizationHeader, MbtiDto.Request request)
            throws Exception {
        System.out.println("ePercent: " + request.getEPercent() +
                ", nPercent: " + request.getNPercent() +
                ", tPercent: " + request.getTPercent() +
                ", jPercent: " + request.getJPercent());

        // 토큰에서 email 정보 추출
        String token = authorizationHeader.substring(7); // "Bearer " 제거

        if (!jwtProvider.validateToken(token)) {
            throw new CustomAuthenticationException("Invalid Access Token");
        }

        String emailFromToken = jwtProvider.getEmailFromToken(token);

        // 사용자 정보 얻기
        User user = userRepository.findByEmail(emailFromToken)
                .orElseThrow(() -> new Exception("User not found"));

        // 데이터 저장
        MbtiResult mbtiResult = MbtiResult.of(user, request.getEPercent(), request.getNPercent(),
                request.getTPercent(), request.getJPercent());
        mbtiResultRepository.save(mbtiResult);
    }
    @Override
    public MbtiDto.LatestResult getLatestMbtiResult(String authorizationHeader) throws Exception {
        // 토큰에서 email 정보 추출
        String token = authorizationHeader.substring(7); // "Bearer " 제거

        if (!jwtProvider.validateToken(token)) {
            throw new CustomAuthenticationException("Invalid Access Token");
        }

        String emailFromToken = jwtProvider.getEmailFromToken(token);

        // 사용자 정보 얻기
        User user = userRepository.findByEmail(emailFromToken)
                .orElseThrow(() -> new Exception("User not found"));

        // 가장 최근의 MBTI 결과를 데이터베이스에서 검색
        MbtiResult latestResult = mbtiResultRepository.findFirstByUserOrderByRegDateDesc(user)
                .orElseThrow(() -> new Exception("No MBTI result found for the user"));

        // DTO 반환
        return new MbtiDto.LatestResult(latestResult.getEPercent(), latestResult.getNPercent(),
                latestResult.getTPercent(), latestResult.getJPercent(), latestResult.getResultMbti());
    }

}

