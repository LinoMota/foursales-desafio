package com.foursales.desafio.adapters.controllers.dtos.order;

import com.foursales.desafio.domain.entities.Order;
import com.foursales.desafio.domain.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record CreateOrderResponseDTO(
        Long id,
        Long userId,
        OrderStatus status,
        List<OrderItemWithPriceDTO> items,
        BigDecimal finalPrice
) {
    public CreateOrderResponseDTO(Order order) {
        this(
                order.getId(),
                order.getUser().getId(),
                order.getStatus(),
                order.getItems().stream()
                        .map(OrderItemWithPriceDTO::new)
                        .collect(Collectors.toList()),
                order.getFinalPrice()
        );
    }
}