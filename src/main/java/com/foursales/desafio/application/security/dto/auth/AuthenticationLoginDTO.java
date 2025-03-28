package com.foursales.desafio.application.security.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationLoginDTO(
        @NotBlank
        String username,

        @NotBlank
        String password
) {
}
