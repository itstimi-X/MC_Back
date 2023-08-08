package com.mini.mbti_collector.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@NoArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private int userIdx;

    @Getter
    @Setter
    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    @Getter
    @Setter
    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Setter
    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "createdAt", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Getter
    @Setter
    @Column(name = "deleted", nullable = false, length = 1)
    private String deleted;
}
