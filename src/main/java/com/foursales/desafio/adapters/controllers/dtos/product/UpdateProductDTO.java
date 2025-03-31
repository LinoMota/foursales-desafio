package com.foursales.desafio.adapters.controllers.dtos.product;

import com.foursales.desafio.domain.entities.Product;
import com.foursales.desafio.domain.enums.Category;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateProductDTO(
        @NotBlank()
        String name,

        @NotNull()
        Category category,

        @NotNull()
        BigDecimal price,

        @NotNull()
        Integer stock
) {

    public Product toEntity(Long id) {
        return new Product(
                id,
                name,
                category,
                price,
                stock
        );
    }

}
