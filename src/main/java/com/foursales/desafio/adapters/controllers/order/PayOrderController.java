package com.foursales.desafio.adapters.controllers.order;

import com.foursales.desafio.adapters.message.dtos.PaymentInfoResponseDTO;
import com.foursales.desafio.domain.usecases.order.PayOrderUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pay-order")
@Tag(name = "Orders")
@SecurityRequirement(name = "bearer-key")
public class PayOrderController {

    @Autowired
    private PayOrderUseCase payOrderUsecase;

    @PostMapping("/{id}")
    public ResponseEntity<PaymentInfoResponseDTO> createOrder(@PathVariable("id") Long orderId) {
        PaymentInfoResponseDTO response = this.payOrderUsecase.execute(orderId);
        return ResponseEntity.ok().body(response);
    }
}
