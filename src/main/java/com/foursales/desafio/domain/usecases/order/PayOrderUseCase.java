package com.foursales.desafio.domain.usecases.order;

import com.foursales.desafio.adapters.message.dtos.PaymentInfoResponseDTO;
import com.foursales.desafio.adapters.message.pub.OrderEventPublisher;
import com.foursales.desafio.domain.entities.Order;
import com.foursales.desafio.infra.database.repositories.OrderRepository;
import com.foursales.desafio.infra.database.repositories.ProductRepository;
import com.foursales.desafio.infra.elasticsearch.repositories.ElasticProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class PayOrderUseCase {

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private OrderEventPublisher orderEventPublisher;

    @Transactional
    public PaymentInfoResponseDTO execute(Long orderId) {
        orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);

        orderEventPublisher.publishOrderPaid(orderId);

        return new PaymentInfoResponseDTO("Payment Successful");
    }

}
