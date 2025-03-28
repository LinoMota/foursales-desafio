package com.foursales.desafio.domain.usecases.order;

import com.foursales.desafio.adapters.controllers.dtos.order.OrderDTO;
import com.foursales.desafio.adapters.controllers.dtos.product.ProductDTO;
import com.foursales.desafio.domain.entities.Order;
import com.foursales.desafio.domain.entities.Product;
import com.foursales.desafio.infra.database.repositories.OrderRepository;
import com.foursales.desafio.infra.database.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetAllOrdersUseCase {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderDTO> execute() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
    }
}
