package com.foursales.desafio.adapters.controllers.dtos.product;

import com.foursales.desafio.domain.entities.Product;
import com.foursales.desafio.domain.enums.Category;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductDTO(
        @Id
        Long id,

        @NotBlank()
        String name,

        @NotNull()
        Category category,

        @NotNull()
        BigDecimal price,

        @NotNull()
        Integer stock
) {

    public ProductDTO(Product product) {
        this(product.getId(), product.getName(), product.getCategory(), product.getPrice(), product.getStock());
    }

    public Product toEntity() {
        return new Product(
                id,
                name,
                category,
                price,
                stock
        );
    }

}
