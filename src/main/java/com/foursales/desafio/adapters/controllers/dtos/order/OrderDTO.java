package com.foursales.desafio.adapters.controllers.dtos.order;

import com.foursales.desafio.domain.entities.Order;
import com.foursales.desafio.domain.enums.OrderStatus;

import java.util.List;
import java.util.stream.Collectors;

public record OrderDTO(
        Long id,
        String username,
        OrderStatus status,
        List<OrderItemDTO> items
) {
    public OrderDTO(Order order) {
        this(
                order.getId(),
                order.getUser().getUsername(),
                order.getStatus(),
                order.getItems().stream()
                        .map(OrderItemDTO::new)
                        .collect(Collectors.toList())
        );
    }
}