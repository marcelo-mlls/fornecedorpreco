package com.marcelo.api.model;

public record RegisterDTO(String login, String password, String email, String cdEmpresa, UserRole role) {

}
