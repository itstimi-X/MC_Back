package com.mini.mbti_collector.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;


@Entity
@Table(name = "User")
@Getter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    private String nickname;

    @Setter
    @Column(unique = true)
    private String email;

    @Setter
    private String password;

    @Column(name = "createdAt", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Setter
    private Boolean deleted;

}
