package com.marcelo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.marcelo.api.model.RefreshToken;
import com.marcelo.api.model.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer> {

    Optional<RefreshToken> findByToken(String token);

    RefreshToken findByUserId(int id);

}
