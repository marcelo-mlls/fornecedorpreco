package com.marcelo.api.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.marcelo.api.model.Users;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generatedToken(Users user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            Date issuedDate = new Date();
            String token = JWT.create()
                    .withIssuer("api_sgutp")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expirationDateToken())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token ", exception);
        }
    }

    public String valideteToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("api_sgutp")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            return "";
        }
    }

    private Instant expirationDateToken(){
        //return LocalDateTime.now().plusDays(60).toInstant(ZoneOffset.of("-03:00"));
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
