package com.foursales.desafio.adapters.controllers.order;

import com.foursales.desafio.adapters.controllers.dtos.order.OrderDTO;
import com.foursales.desafio.adapters.controllers.dtos.product.ProductDTO;
import com.foursales.desafio.domain.usecases.order.GetAllOrdersUseCase;
import com.foursales.desafio.domain.usecases.product.GetAllProductsUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Orders")
@SecurityRequirement(name = "bearer-key")
public class GetAllOrdersController {
    @Autowired
    private GetAllOrdersUseCase getAllOrdersUseCase;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> response = getAllOrdersUseCase.execute();
        return ResponseEntity.ok(response);
    }
}
