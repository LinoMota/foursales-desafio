package com.foursales.desafio.domain.usecases.order;

import com.foursales.desafio.adapters.controllers.dtos.order.CreateOrderDTO;
import com.foursales.desafio.adapters.controllers.dtos.order.CreateOrderResponseDTO;
import com.foursales.desafio.adapters.message.dtos.OrderEventDTO;
import com.foursales.desafio.adapters.message.pub.OrderEventPublisher;
import com.foursales.desafio.domain.entities.Order;
import com.foursales.desafio.domain.entities.OrderItem;
import com.foursales.desafio.domain.entities.Product;
import com.foursales.desafio.domain.enums.OrderStatus;
import com.foursales.desafio.infra.database.repositories.OrderRepository;
import com.foursales.desafio.infra.database.repositories.ProductRepository;
import com.foursales.desafio.infra.database.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@Component
public class CreateOrderUseCase {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderEventPublisher orderEventPublisher;

    @Transactional
    public CreateOrderResponseDTO execute(CreateOrderDTO createOrderDTO, String username) {

        var user = userRepository.findOneByUsername(username);

        Order order = new Order();

        order.setUser(user);

        AtomicBoolean isValid = new AtomicBoolean(true);
        AtomicReference<BigDecimal> finalPrice = new AtomicReference<>(BigDecimal.ZERO);

        List<OrderItem> orderItems = createOrderDTO.items().stream().map(itemDTO -> {
            Product product = productRepository.findById(itemDTO.productId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found: " + itemDTO.productId()));

            BigDecimal price = product.getPrice().multiply(new BigDecimal(itemDTO.quantity()));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.quantity());
            orderItem.setPrice(price);

            finalPrice.set(finalPrice.get().add(price));


            if (orderItem.getQuantity() > product.getStock()) {
                isValid.set(false);
            }

            return orderItem;
        }).collect(Collectors.toList());

        order.setStatus(isValid.get() ? OrderStatus.PENDING : OrderStatus.CANCELLED);

        order.setItems(orderItems);
        order.setCreatedAt(LocalDateTime.now());
        order.setFinalPrice(finalPrice.get());

        Order savedOrder = orderRepository.save(order);

        if (isValid.get())
            orderEventPublisher.publishOrderCreated(
                    savedOrder.getId()
            );

        return new CreateOrderResponseDTO(savedOrder);
    }

}
