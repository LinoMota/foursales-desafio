package com.foursales.desafio.infra.database.repositories;

import com.foursales.desafio.domain.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
