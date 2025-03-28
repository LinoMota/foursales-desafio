package com.foursales.desafio.domain.usecases.order;

import com.foursales.desafio.adapters.controllers.dtos.order.OrderDTO;
import com.foursales.desafio.adapters.controllers.dtos.user.UserDTO;
import com.foursales.desafio.domain.entities.Order;
import com.foursales.desafio.domain.entities.User;
import com.foursales.desafio.infra.database.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GetOrderUseCase {

    @Autowired
    private OrderRepository orderRepository;

    public OrderDTO execute(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);

        return orderOptional.stream().findFirst().map(OrderDTO::new).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }
}
