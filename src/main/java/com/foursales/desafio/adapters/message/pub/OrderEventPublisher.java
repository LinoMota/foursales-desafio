package com.foursales.desafio.adapters.message.pub;

import com.foursales.desafio.adapters.message.dtos.OrderEventDTO;
import com.foursales.desafio.adapters.message.dtos.ProductEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OrderEventPublisher {

    @Autowired
    private KafkaTemplate<String, Long> kafkaTemplate;

    @Value("${kafka-topics.created-order}")
    private String createdOrderTopic;

    @Value("${kafka-topics.paid-order}")
    private String paidOrderTopic;


    public void publishOrderCreated(@Payload Long orderId) {
        kafkaTemplate.send(createdOrderTopic,  orderId);
    }

    public void publishOrderPaid(@Payload Long orderId) {
        kafkaTemplate.send(paidOrderTopic, orderId);
    }
}