package com.foursales.desafio.infra.database.repositories;

import com.foursales.desafio.adapters.controllers.dtos.queries.TopUserPurchasesDTO;
import com.foursales.desafio.adapters.controllers.dtos.queries.UserAverageTicketDTO;
import com.foursales.desafio.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

   UserDetails findByUsername(String username);

   User findOneByUsername(String username);

   @Query("""
        SELECT u.id AS userId, u.username AS username, COUNT(o.id) AS totalOrders 
        FROM User u 
        JOIN Order o ON u.id = o.user.id 
        WHERE o.status = 'PAID' 
        GROUP BY u.id 
        ORDER BY totalOrders DESC
        LIMIT 5
    """)
   List<TopUserPurchasesDTO> findTopUsersByPurchases();


   @Query("""
    SELECT u.id AS userId,
           u.username AS username,
           COALESCE(AVG(oi.quantity * oi.price), 0) AS averageTicket
    FROM User u
    JOIN Order o ON u.id = o.user.id
    JOIN OrderItem oi ON o.id = oi.order.id
    WHERE o.status = 'PAID'
    GROUP BY u.id, u.username
""")
   List<UserAverageTicketDTO> findUsersAverageTicket();
}
