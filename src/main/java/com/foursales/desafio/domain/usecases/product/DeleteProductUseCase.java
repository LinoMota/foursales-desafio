package com.foursales.desafio.domain.usecases.product;

import com.foursales.desafio.adapters.controllers.dtos.product.ProductDTO;
import com.foursales.desafio.domain.entities.Product;
import com.foursales.desafio.infra.database.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class DeleteProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    public ProductDTO execute(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        productRepository.delete(product);

        return new ProductDTO(product);
    }

}
