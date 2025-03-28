package com.foursales.desafio.domain.usecases.user;

import com.foursales.desafio.adapters.controllers.dtos.user.UserDTO;
import com.foursales.desafio.domain.entities.User;
import com.foursales.desafio.infra.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetAllUsersUseCase {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> execute() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }
}