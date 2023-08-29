package com.mini.mbti_collector.domain;

import com.mini.mbti_collector.dto.MbtiDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mbti_results")
public class MbtiResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mbti_result_idx")
    @Getter
    private int mbtiResultIdx;

    //해당 필드는 User 엔티티의 인스턴스를 참조하고 있기 때문에 이름을 user로 설정하는 것이 좀 더 명확하게 의미를 전달함
    @ManyToOne
    @JoinColumn(name = "user_idx")
    @Getter @Setter
    private User user;

    @Column(name = "e_percent")
    @Getter @Setter
    private int ePercent;

    @Column(name = "n_percent")
    @Getter @Setter
    private int nPercent;

    @Column(name = "t_percent")
    @Getter @Setter
    private int tPercent;

    @Column(name = "j_percent")
    @Getter @Setter
    private int jPercent;

    @Column(name = "result_mbti", length = 4)
    @Getter @Setter
    private String resultMbti;

    @Column(name = "reg_date", nullable = false, columnDefinition = "TIMESTAMP")
    @Getter @Setter
    private LocalDateTime regDate;

    public static MbtiResult of(User user, int ePercent, int nPercent, int tPercent, int jPercent) {
        MbtiResult mbtiResult = new MbtiResult();
        mbtiResult.setUser(user);
        mbtiResult.setEPercent(ePercent);
        mbtiResult.setNPercent(nPercent);
        mbtiResult.setTPercent(tPercent);
        mbtiResult.setJPercent(jPercent);

        StringBuilder resultMbti = new StringBuilder();
        resultMbti.append(ePercent >= 50 ? "E" : "I");
        resultMbti.append(nPercent >= 50 ? "N" : "S");
        resultMbti.append(tPercent >= 50 ? "T" : "F");
        resultMbti.append(jPercent >= 50 ? "J" : "P");

        mbtiResult.setResultMbti(resultMbti.toString());

        // Setting regDate to current time.
        mbtiResult.setRegDate(LocalDateTime.now());

        return mbtiResult;
    }
}
