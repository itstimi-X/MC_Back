package com.mini.mbti_collector.domain;

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
    @Getter
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
    @Getter
    private LocalDateTime regDate;


}
