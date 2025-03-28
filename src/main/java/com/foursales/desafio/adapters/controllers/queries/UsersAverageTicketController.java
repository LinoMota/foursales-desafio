package com.foursales.desafio.adapters.controllers.queries;

import com.foursales.desafio.adapters.controllers.dtos.queries.UserAverageTicketDTO;
import com.foursales.desafio.domain.usecases.queries.UsersAverageTicketByUserUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users-average-ticket")
@Tag(name = "Queries")
@SecurityRequirement(name = "bearer-key")
public class UsersAverageTicketController {

    @Autowired
    private UsersAverageTicketByUserUseCase usersAverageTicketByUserUseCase;

    @GetMapping
    public List<UserAverageTicketDTO> execute() {
        return usersAverageTicketByUserUseCase.execute();
    }
}
