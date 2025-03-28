package com.foursales.desafio.domain.usecases.queries;

import com.foursales.desafio.adapters.controllers.dtos.queries.CurrentMonthRevenueDTO;
import com.foursales.desafio.adapters.controllers.dtos.queries.TopUserPurchasesDTO;
import com.foursales.desafio.infra.database.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TotalMonthRevenueUseCase {

    @Autowired
    private OrderRepository orderRepository;

    public CurrentMonthRevenueDTO execute() {
        return new CurrentMonthRevenueDTO(orderRepository.findTotalRevenueForCurrentMonth());
    }
}
