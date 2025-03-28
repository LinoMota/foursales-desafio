package com.foursales.desafio.adapters.controllers.user;

import com.foursales.desafio.adapters.controllers.dtos.user.CreateUserDTO;
import com.foursales.desafio.adapters.controllers.dtos.user.UserDTO;
import com.foursales.desafio.domain.usecases.user.CreateUserUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
@Tag(name = "Users")
@SecurityRequirement(name = "bearer-key")
public class CreateUserController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid CreateUserDTO user) {
        UserDTO response = this.createUserUseCase.execute(user);
        return ResponseEntity.created(URI.create("/users/" + response.id()))
                .body(response);
    }
}
