package com.foursales.desafio.adapters.controllers.order;

import com.foursales.desafio.adapters.controllers.dtos.order.OrderDTO;
import com.foursales.desafio.adapters.message.dtos.PaymentInfoResponseDTO;
import com.foursales.desafio.domain.usecases.order.GetUserRelatedOrdersUseCase;
import com.foursales.desafio.domain.usecases.order.PayOrderUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/my-orders")
@Tag(name = "Orders")
@SecurityRequirement(name = "bearer-key")
public class GetUserRelatedOrdersController {

    @Autowired
    private GetUserRelatedOrdersUseCase getUserRelatedOrdersUseCase;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> createOrder(Authentication authentication) {
        String username = authentication.getPrincipal().toString();
        List<OrderDTO> response = this.getUserRelatedOrdersUseCase.execute(username);
        return ResponseEntity.ok().body(response);
    }
}
