package com.foursales.desafio.adapters.controllers.user;

import com.foursales.desafio.adapters.controllers.dtos.user.UserDTO;
import com.foursales.desafio.domain.usecases.user.GetOneUserUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "Users")
@SecurityRequirement(name = "bearer-key")
public class GetOneUsersController {

    @Autowired
    private GetOneUserUseCase getOneUserUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getOneProduct(@PathVariable Long id) {
        return ResponseEntity.ok(getOneUserUseCase.execute(id));
    }
}
