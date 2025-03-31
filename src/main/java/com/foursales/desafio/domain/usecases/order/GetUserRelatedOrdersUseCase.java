package com.foursales.desafio.domain.usecases.order;

import com.foursales.desafio.adapters.controllers.dtos.order.OrderDTO;
import com.foursales.desafio.adapters.message.dtos.PaymentInfoResponseDTO;
import com.foursales.desafio.adapters.message.pub.OrderEventPublisher;
import com.foursales.desafio.domain.entities.Order;
import com.foursales.desafio.domain.entities.User;
import com.foursales.desafio.domain.enums.OrderStatus;
import com.foursales.desafio.domain.exceptions.CantPayInvalidException;
import com.foursales.desafio.domain.exceptions.OtherUserTriedToPayTheOrderException;
import com.foursales.desafio.infra.database.repositories.OrderRepository;
import com.foursales.desafio.infra.database.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class GetUserRelatedOrdersUseCase {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<OrderDTO> execute(String username) {
        User user = userRepository.findOneByUsername(username);

        List<Order> userOrders = orderRepository.findOrdersByUserId(user.getId());

        return userOrders.stream().map(OrderDTO::new).collect(Collectors.toList());
    }

}
