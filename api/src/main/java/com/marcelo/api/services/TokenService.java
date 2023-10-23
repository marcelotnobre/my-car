package com.marcelo.api.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.marcelo.api.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final String EMISSOR = "auth-api";

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm auAlgorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(EMISSOR)
                    .withSubject(user.getLogin())
                    .withExpiresAt(generationExpirationDate())
                    .sign(auAlgorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm auAlgorithm = Algorithm.HMAC256(secret);
            return JWT.require(auAlgorithm)
                    .withIssuer(EMISSOR)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant generationExpirationDate() {
        return LocalDateTime.now().plusHours(10).toInstant(ZoneOffset.of("-03:00"));
    }
}
