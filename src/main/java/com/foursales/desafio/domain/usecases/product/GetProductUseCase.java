package com.foursales.desafio.domain.usecases.product;

import com.foursales.desafio.adapters.controllers.dtos.product.ProductDTO;
import com.foursales.desafio.domain.entities.Product;
import com.foursales.desafio.infra.database.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class GetProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    public ProductDTO execute(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        return productOptional.stream().findFirst().map(ProductDTO::new).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }
}
