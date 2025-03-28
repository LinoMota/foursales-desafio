package com.foursales.desafio.adapters.controllers.dtos.order;

import com.foursales.desafio.domain.entities.OrderItem;

import java.math.BigDecimal;

public record OrderItemWithPriceDTO(
        Long productId,
        Integer quantity,
        BigDecimal price
) {
    public OrderItemWithPriceDTO(OrderItem orderItem) {
        this(orderItem.getProduct().getId(), orderItem.getQuantity(), orderItem.getPrice());
    }
}
