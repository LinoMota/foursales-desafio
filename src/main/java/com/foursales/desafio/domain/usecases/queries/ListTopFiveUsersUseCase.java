package com.foursales.desafio.domain.usecases.queries;

import com.foursales.desafio.adapters.controllers.dtos.queries.TopUserPurchasesDTO;
import com.foursales.desafio.infra.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListTopFiveUsersUseCase {

    @Autowired
    private UserRepository userRepository;

    public List<TopUserPurchasesDTO> execute() {
        return userRepository.findTopUsersByPurchases();
    }
}
