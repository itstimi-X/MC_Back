package com.mini.mbti_collector.config;

import com.mini.mbti_collector.error.FilterExceptionHandler;
import com.mini.mbti_collector.jwt.JwtFilter;
import com.mini.mbti_collector.jwt.JwtProvider;
import com.mini.mbti_collector.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private JwtFilter jwtAuthorizationFilter = jwtAuthorizationFilter();
    ;
    JwtFilter jwtAuthorizationFilter() {
        return new JwtFilter(jwtProvider, userRepository);
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CorsFilter corsFilter) throws Exception {

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
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(corsFilter, ChannelProcessingFilter.class); // CORS 필터 추가

        // 예외처리
        http
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint((request, response, authException) -> {
                            // 인증문제가 발생했을 때 이 부분을 호출한다.
                            response.setStatus(401);
                            response.setCharacterEncoding("utf-8");
                            response.setContentType("text/html; charset=UTF-8");
                            response.getWriter().write("인증되지 않은 사용자입니다.");
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            // 권한 문제가 발생했을 때 이 부분을 호출한다.
                            response.setStatus(403);
                            response.setCharacterEncoding("utf-8");
                            response.setContentType("text/html; charset=UTF-8");
                            response.getWriter().write("권한이 없는 사용자입니다.");
                        })
                )
                .addFilterBefore(jwtAuthorizationFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(new FilterExceptionHandler(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://127.0.0.1:5500","https://localhost:8080"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS", "PATCH"));
        configuration.setAllowCredentials(true); // 쿠키 전송을 허용합니다.
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 대해 CORS 설정을 적용합니다.

        return source;
    }

    @Bean
    public CorsFilter corsFilter(CorsConfigurationSource corsConfigurationSource) {
        return new CorsFilter(corsConfigurationSource);
    }

}
