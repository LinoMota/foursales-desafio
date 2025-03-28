package com.foursales.desafio.domain.usecases.product;

import com.foursales.desafio.adapters.controllers.dtos.product.CreateProductDTO;
import com.foursales.desafio.adapters.controllers.dtos.product.CreateProductResponseDTO;
import com.foursales.desafio.adapters.message.pub.ProductEventPublisher;
import com.foursales.desafio.domain.entities.Product;
import com.foursales.desafio.infra.database.repositories.ProductRepository;
import com.foursales.desafio.infra.elasticsearch.models.ProductModel;
import com.foursales.desafio.infra.elasticsearch.repositories.ElasticProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductEventPublisher productEventPublisher;

    @Transactional
    public CreateProductResponseDTO execute(CreateProductDTO dto) {
        Product created = productRepository.save(dto.toEntity());
        productEventPublisher.publishProductCreated(created.getId());
        return new CreateProductResponseDTO(created);
    }

}
