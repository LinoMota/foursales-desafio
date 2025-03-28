package com.foursales.desafio.domain.usecases.user;

import com.foursales.desafio.adapters.controllers.dtos.user.UserDTO;
import com.foursales.desafio.domain.entities.User;
import com.foursales.desafio.infra.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class GetOneUserUseCase {

    @Autowired
    private UserRepository userRepository;

    public UserDTO execute(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        return userOptional.stream().findFirst().map(UserDTO::new).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
    }
}
