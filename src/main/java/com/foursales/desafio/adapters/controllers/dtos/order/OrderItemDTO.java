package com.foursales.desafio.adapters.controllers.dtos.order;

import com.foursales.desafio.domain.entities.OrderItem;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record OrderItemDTO(
        Long productId,
        Integer quantity
) {
    public OrderItemDTO(OrderItem orderItem) {
        this(orderItem.getProduct().getId(), orderItem.getQuantity());
    }
}
