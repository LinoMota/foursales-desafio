package com.foursales.desafio.adapters.controllers.dtos.user;

import com.foursales.desafio.domain.entities.User;
import com.foursales.desafio.domain.enums.Role;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
        Long id,
        String username,
        Role role
) {

    public User toEntity() {
        return new User(id, username, null, role);
    }

    public UserDTO(User user){
        this(user.getId(), user.getUsername(), user.getRole());
    }
}