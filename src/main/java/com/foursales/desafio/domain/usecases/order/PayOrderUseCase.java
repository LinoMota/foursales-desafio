package com.foursales.desafio.domain.usecases.order;

import com.foursales.desafio.adapters.message.dtos.PaymentInfoResponseDTO;
import com.foursales.desafio.adapters.message.pub.OrderEventPublisher;
import com.foursales.desafio.domain.entities.Order;
import com.foursales.desafio.domain.entities.User;
import com.foursales.desafio.domain.enums.OrderStatus;
import com.foursales.desafio.domain.exceptions.CantPayInvalidException;
import com.foursales.desafio.domain.exceptions.OtherUserTriedToPayTheOrderException;
import com.foursales.desafio.infra.database.repositories.OrderRepository;
import com.foursales.desafio.infra.database.repositories.ProductRepository;
import com.foursales.desafio.infra.database.repositories.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public PaymentInfoResponseDTO execute(Long orderId, String username) {

        User user = userRepository.findOneByUsername(username);

        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);

        if (!order.getUser().getUsername().equals(user.getUsername())) throw new CantPayInvalidException();

        if (order.getStatus() == OrderStatus.CANCELLED || order.getStatus() == OrderStatus.PAID)
            throw new OtherUserTriedToPayTheOrderException();

        orderEventPublisher.publishOrderPaid(orderId);

        return new PaymentInfoResponseDTO("Payment Successful");
    }

}
