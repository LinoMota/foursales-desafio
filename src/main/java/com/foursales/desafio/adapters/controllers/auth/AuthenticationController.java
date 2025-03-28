package com.foursales.desafio.adapters.controllers.auth;

import com.foursales.desafio.application.security.LoginUseCase;
import com.foursales.desafio.application.security.dto.auth.AuthenticationLoginDTO;
import com.foursales.desafio.application.security.dto.auth.TokenResponseJWT;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class AuthenticationController {

    @Autowired
    private LoginUseCase loginUseCase;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseJWT> login(@RequestBody @Valid AuthenticationLoginDTO data) {
        var token = loginUseCase.execute(data);
        return ResponseEntity.ok(token);
    }
}
