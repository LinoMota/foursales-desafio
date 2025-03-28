package com.foursales.desafio.domain.usecases.product;

import com.foursales.desafio.adapters.controllers.dtos.product.ProductDTO;
import com.foursales.desafio.adapters.controllers.dtos.product.UpdateProductDTO;
import com.foursales.desafio.adapters.message.pub.ProductEventPublisher;
import com.foursales.desafio.domain.entities.Product;
import com.foursales.desafio.infra.database.repositories.ProductRepository;
import com.foursales.desafio.infra.elasticsearch.models.ProductModel;
import com.foursales.desafio.infra.elasticsearch.repositories.ElasticProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class UpdateProductUseCase {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductEventPublisher productEventPublisher;

    public ProductDTO execute(Long id, UpdateProductDTO updateProductDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        if (updateProductDTO.name() != null && !updateProductDTO.name().trim().isEmpty()) {
            product.setName(updateProductDTO.name());
        }

        if (updateProductDTO.category() != null && !updateProductDTO.category().trim().isEmpty()) {
            product.setCategory(updateProductDTO.category());
        }

        if (updateProductDTO.stock() != null){
            product.setStock(updateProductDTO.stock());
        }

        if (updateProductDTO.price() != null){
            product.setPrice(updateProductDTO.price());
        }

        productRepository.save(product);

        productEventPublisher.publishProductUpdated(product.getId());

        return new ProductDTO(product);
    }
}
