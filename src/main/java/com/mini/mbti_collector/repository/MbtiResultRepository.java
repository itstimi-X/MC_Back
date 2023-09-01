package com.mini.mbti_collector.repository;

import com.mini.mbti_collector.domain.MbtiResult;
import com.mini.mbti_collector.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MbtiResultRepository extends JpaRepository<MbtiResult, Integer> {

    Optional<MbtiResult> findFirstByUserOrderByRegDateDesc(User user);

    // 사용자에 대한 날짜별 MBTI 수치 조회
    List<MbtiResult> findByUserOrderByRegDate(User user);
}
