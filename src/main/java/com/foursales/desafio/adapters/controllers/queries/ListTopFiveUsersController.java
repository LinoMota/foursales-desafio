package com.foursales.desafio.adapters.controllers.queries;

import com.foursales.desafio.adapters.controllers.dtos.queries.TopUserPurchasesDTO;
import com.foursales.desafio.domain.entities.Product;
import com.foursales.desafio.domain.usecases.queries.ListTopFiveUsersUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/top-five-users")
@Tag(name = "Queries")
@SecurityRequirement(name = "bearer-key")
public class ListTopFiveUsersController {

    @Autowired
    private ListTopFiveUsersUseCase listTopFiveUsersUseCase;

    @GetMapping
    public List<TopUserPurchasesDTO> execute() {
        var result = listTopFiveUsersUseCase.execute();

        return result;
    }
}
