package com.foursales.desafio.application.security;

import com.foursales.desafio.application.security.dto.auth.AuthenticationLoginDTO;
import com.foursales.desafio.application.security.dto.auth.TokenResponseJWT;
import com.foursales.desafio.domain.entities.User;
import com.foursales.desafio.infra.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class LoginUseCase {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    public TokenResponseJWT execute(AuthenticationLoginDTO data) {
        var user = userRepository.findByUsername(data.username());

        var authenticationToken = new UsernamePasswordAuthenticationToken(data.username(), data.password(), user.getAuthorities());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return new TokenResponseJWT(tokenJWT);
    }
}
