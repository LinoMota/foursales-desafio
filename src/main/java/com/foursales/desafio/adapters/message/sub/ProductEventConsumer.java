package com.foursales.desafio.adapters.message.sub;

import com.foursales.desafio.adapters.message.dtos.ProductEventDTO;
import com.foursales.desafio.domain.entities.Product;
import com.foursales.desafio.infra.database.repositories.ProductRepository;
import com.foursales.desafio.infra.elasticsearch.models.ProductModel;
import com.foursales.desafio.infra.elasticsearch.repositories.ElasticProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductEventConsumer {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ElasticProductRepository elasticProductRepository;

    @KafkaListener(topics = "${kafka-topics.created-product}", groupId = "product-group")
    @Transactional
    public void handleProductCreated(@Payload  ProductEventDTO productEventDTO) {
        System.out.println("Received product created event: " + productEventDTO.productId());

        Product product = productRepository.findById(productEventDTO.productId()).orElseThrow(() -> new RuntimeException("Product not found"));

        elasticProductRepository.save(new ProductModel(product));
    }

    @KafkaListener(topics = "${kafka-topics.updated-product}", groupId = "product-group")
    @Transactional
    public void handleProductUpdated(@Payload  ProductEventDTO productEventDTO) {
        System.out.println("Received product updated event: " + productEventDTO.productId());

        Product product = productRepository.findById(productEventDTO.productId()).orElseThrow(() -> new RuntimeException("Product not found"));

        ProductModel productModel = elasticProductRepository.findById(productEventDTO.productId()).orElseThrow(() -> new RuntimeException("Product not found"));

        productModel.setStock(product.getStock());
        productModel.setPrice(product.getPrice());
        productModel.setName(product.getName());
        productModel.setCategory(product.getCategory());

        elasticProductRepository.save(productModel);
    }

}
