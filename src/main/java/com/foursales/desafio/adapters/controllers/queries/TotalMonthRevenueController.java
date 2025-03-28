package com.foursales.desafio.adapters.controllers.queries;

import com.foursales.desafio.adapters.controllers.dtos.queries.CurrentMonthRevenueDTO;
import com.foursales.desafio.adapters.controllers.dtos.queries.UserAverageTicketDTO;
import com.foursales.desafio.domain.usecases.queries.TotalMonthRevenueUseCase;
import com.foursales.desafio.domain.usecases.queries.UsersAverageTicketByUserUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/current-month-revenue")
@Tag(name = "Queries")
@SecurityRequirement(name = "bearer-key")
public class TotalMonthRevenueController {

    @Autowired
    private TotalMonthRevenueUseCase totalMonthRevenueUseCase;

    @GetMapping
    public CurrentMonthRevenueDTO execute() {
        return totalMonthRevenueUseCase.execute();
    }
}
