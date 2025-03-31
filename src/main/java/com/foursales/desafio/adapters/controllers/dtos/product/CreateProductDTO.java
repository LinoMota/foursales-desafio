package com.foursales.desafio.adapters.controllers.dtos.product;

import com.foursales.desafio.domain.entities.Product;
import com.foursales.desafio.domain.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateProductDTO(

        @NotBlank()
        String name,

        @NotNull()
        Category category,

        @NotNull()
        BigDecimal price,

        @NotNull()
        Integer stock
) {
    public Product toEntity() {
        return new Product(
                null,
                name,
                category,
                price,
                stock
        );
    }

    public CreateProductDTO(Product product) {
        this(
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getStock()
        );
    }
}