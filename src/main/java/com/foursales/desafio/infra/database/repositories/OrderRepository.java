package com.foursales.desafio.infra.database.repositories;

import com.foursales.desafio.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""
                SELECT COALESCE(SUM(oi.quantity * oi.price), 0) AS totalRevenue
                FROM Order o
                JOIN OrderItem oi ON o.id = oi.order.id
                WHERE o.status = 'PAID'
                  AND MONTH(o.createdAt) = MONTH(CURRENT_DATE)
                  AND YEAR(o.createdAt) = YEAR(CURRENT_DATE)
            """)
    Double findTotalRevenueForCurrentMonth();

    @Query("""
            SELECT o
            FROM Order o
            JOIN o.items oi
            WHERE oi.product.id = :productId
        """)
    List<Order> findOrdersByProductId(@Param("productId") Long productId);


    List<Order> findOrdersByUserId(Long userId);

}
