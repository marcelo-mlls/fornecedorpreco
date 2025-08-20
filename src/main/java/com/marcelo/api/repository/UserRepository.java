package com.marcelo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelo.api.model.Users;


public interface UserRepository extends JpaRepository<Users,Integer> {

    Users findByLogin(String login);
}
