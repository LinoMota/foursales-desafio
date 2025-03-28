package com.foursales.desafio.domain.usecases.product;

import com.foursales.desafio.adapters.controllers.dtos.product.ProductDTO;
import com.foursales.desafio.domain.entities.Product;
import com.foursales.desafio.infra.database.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetAllProductsUseCase {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> execute() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }
}
