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
@Table(name = "RefreshTokens")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private int tokenId;

    @Getter
    @Setter
    @Column(name = "token_value", nullable = false, length = 255)
    private String tokenValue;

    @Setter
    @Column(name = "expiration_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime expirationDate;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private User user;

    // ... 기타 필요한 필드와 메서드
}
