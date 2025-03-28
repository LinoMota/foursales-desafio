package com.foursales.desafio.adapters.controllers.order;

import com.foursales.desafio.adapters.controllers.dtos.order.CreateOrderDTO;
import com.foursales.desafio.adapters.controllers.dtos.order.CreateOrderResponseDTO;
import com.foursales.desafio.domain.usecases.order.CreateOrderUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/orders")
@Tag(name = "Orders")
@SecurityRequirement(name = "bearer-key")
public class CreateOrderController {

    @Autowired
    private CreateOrderUseCase createOrderUseCase;

    @PostMapping
    public ResponseEntity<CreateOrderResponseDTO> createOrder(@RequestBody @Valid CreateOrderDTO order, Authentication authentication) {
        String username = authentication.getPrincipal().toString();
        CreateOrderResponseDTO response = this.createOrderUseCase.execute(order, username);
        return ResponseEntity.created(URI.create("/orders/" + response.id())).body(response);
    }

}
