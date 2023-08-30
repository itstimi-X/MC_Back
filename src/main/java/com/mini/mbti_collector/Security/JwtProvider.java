package com.mini.mbti_collector.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.token-expiration}")
    private long jwtExpirationInMs;  // Renamed variable for consistency

    public String createAccessToken(String email, String nickname) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return JWT.create()
                .withSubject(email)
                .withClaim("nickname", nickname)
                .withIssuedAt(now)
                .withExpiresAt(expiryDate)
                .sign(Algorithm.HMAC512(jwtSecret));
    }

    public String generateToken(String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(now)
                .withExpiresAt(expiryDate)
                .sign(Algorithm.HMAC512(jwtSecret));
    }

    public String getSubjectFromToken(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC512(jwtSecret))
                .build()
                .verify(token);
        return jwt.getSubject();
    }

    public String getEmailFromToken(String token) {
        return getSubjectFromToken(token);  // Email is stored in subject
    }

    public boolean validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(jwtSecret)).build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            // Token is invalid or expired
            return false;
        }
    }
}
