package com.foursales.desafio.adapters.controllers.dtos.user;

import com.foursales.desafio.domain.entities.User;
import com.foursales.desafio.domain.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDTO(
        @NotBlank String username,
        @NotBlank String password,
        @NotNull String role
) {
        public User toEntity() {
                return new User(
                        null,
                        this.username(),
                        this.password(),
                        Role.valueOf(this.role().toUpperCase())
                );
        }

        public static UserDTO toDTO(User user) {
                return new UserDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getRole()
                );
        }
}