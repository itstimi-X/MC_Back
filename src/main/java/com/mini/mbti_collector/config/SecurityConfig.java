package com.mini.mbti_collector.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .cors(cors -> {
                    try {
                        cors.configurationSource(corsConfigurationSource()); // 원하는 CORS 구성을 사용
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("chrome-extension://fhbjgbiflinjbdggehcddcbncdddomop", "http://127.0.0.1:5500"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS", "PATCH"));

        // 허용될 헤더들을 설정합니다.
        configuration.setAllowedHeaders(List.of(
                "Accept",
                "Content-Type",
                "Referer",
                "Sec-Ch-Ua",
                "Sec-Ch-Ua-Mobile",
                "Sec-Ch-Ua-Platform",
                "User-Agent"
        ));

        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
