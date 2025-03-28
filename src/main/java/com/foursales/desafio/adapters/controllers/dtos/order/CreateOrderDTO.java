package com.foursales.desafio.adapters.controllers.dtos.order;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderDTO(
        @NotNull()
        List<OrderItemDTO> items
) {

}
