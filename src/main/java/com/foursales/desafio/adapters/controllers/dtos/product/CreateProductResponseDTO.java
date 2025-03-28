package com.foursales.desafio.adapters.controllers.dtos.product;

import com.foursales.desafio.domain.entities.Product;

import java.math.BigDecimal;

public record CreateProductResponseDTO(Long id, String name, String category, BigDecimal price, Integer stock) {

    public CreateProductResponseDTO(Product product) {
        this(product.getId(), product.getName(), product.getCategory(), product.getPrice(), product.getStock());
    }

}