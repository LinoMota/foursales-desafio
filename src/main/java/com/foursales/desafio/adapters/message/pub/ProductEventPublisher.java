package com.foursales.desafio.adapters.message.pub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ProductEventPublisher {

    @Autowired
    private KafkaTemplate<Object, Long> kafkaTemplate;

    @Value("${kafka-topics.created-product}")
    private String createdProductTopic;

    @Value("${kafka-topics.updated-product}")
    private String updatedProductTopic;

    public void publishProductCreated(@Payload Long productId) {
        kafkaTemplate.send(createdProductTopic,  productId);
    }

    public void publishProductUpdated(@Payload Long productId) {
        kafkaTemplate.send(updatedProductTopic, productId);
    }
}