package com.foursales.desafio.domain.usecases.user;

import com.foursales.desafio.adapters.controllers.dtos.user.CreateUserDTO;
import com.foursales.desafio.adapters.controllers.dtos.user.UserDTO;
import com.foursales.desafio.domain.entities.User;
import com.foursales.desafio.infra.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateUserUseCase {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserDTO execute(CreateUserDTO createUserDTO) {
        User createdUser = createUserDTO.toEntity();
        createdUser.setPassword(createUserDTO.password());
        return new UserDTO(this.userRepository.save(createdUser));
    }
}
