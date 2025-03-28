package com.foursales.desafio.adapters.message.sub;

import com.foursales.desafio.adapters.message.dtos.OrderEventDTO;
import com.foursales.desafio.adapters.message.dtos.ProductEventDTO;
import com.foursales.desafio.adapters.message.pub.OrderEventPublisher;
import com.foursales.desafio.domain.entities.Order;
import com.foursales.desafio.domain.enums.OrderStatus;
import com.foursales.desafio.infra.database.repositories.OrderRepository;
import com.foursales.desafio.infra.database.repositories.ProductRepository;
import com.foursales.desafio.infra.elasticsearch.repositories.ElasticProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderEventConsumer {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ElasticProductRepository elasticProductRepository;

    @KafkaListener(topics = "${kafka-topics.created-order}", groupId = "order-group")
    @Transactional
    public void handleOrderPaid(@Payload OrderEventDTO orderEventDTO) {
        Order order = orderRepository.findById(orderEventDTO.orderId()).orElseThrow(EntityNotFoundException::new);

        boolean isValid = true;

        for (var item : order.getItems()) {
            var product = item.getProduct();
            var newStock = product.getStock() - item.getQuantity();
            if (newStock < 0) {
                isValid = false;
                break;
            }
        }

        order.setStatus(isValid ? OrderStatus.PENDING : OrderStatus.CANCELLED);

        orderRepository.save(order);
    }

    @KafkaListener(topics = "order.paid", groupId = "order-group")
    @Transactional
    public void handleOrderEvent(@Payload OrderEventDTO orderEventDTO) {
        Order order = orderRepository.findById(orderEventDTO.orderId()).orElseThrow(EntityNotFoundException::new);

        order.setStatus(OrderStatus.PAID);

        order.getItems().forEach(item -> {
            var product = item.getProduct();
            var elasticProduct = elasticProductRepository.findById(product.getId()).orElseThrow(EntityNotFoundException::new);

            var newStock = product.getStock() - item.getQuantity();

            elasticProduct.setStock(newStock);
            product.setStock(newStock);

            elasticProductRepository.save(elasticProduct);
            productRepository.save(product);
        });

        orderRepository.save(order);
    }

}