package com.marcelo.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelo.api.model.RefreshToken;
import com.marcelo.api.model.Users;
import com.marcelo.api.repository.RefreshTokenRepository;
import com.marcelo.api.repository.UserRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;


@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(userRepository.findByLogin(username))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusSeconds(86400))//24 horas
                .build();
        return refreshTokenRepository.save(refreshToken);
    }



    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token expirado. Faça o login novamente");
        }
        return token;
    }

    public void limpaToken(Users usuarioLogado){
        RefreshToken token = refreshTokenRepository.findByUserId(usuarioLogado.getId());
        if(token.getToken() != null){
            refreshTokenRepository.delete(token);
        }
    }

}
