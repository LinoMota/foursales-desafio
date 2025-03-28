package com.foursales.desafio.infra.database.repositories;

import com.foursales.desafio.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

}
