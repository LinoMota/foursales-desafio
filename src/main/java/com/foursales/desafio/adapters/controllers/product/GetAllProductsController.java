package com.foursales.desafio.adapters.controllers.product;

import com.foursales.desafio.adapters.controllers.dtos.product.ProductDTO;
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
@RequestMapping("/products")
@Tag(name = "Products")
@SecurityRequirement(name = "bearer-key")
public class GetAllProductsController {

    @Autowired
    private GetAllProductsUseCase getAllProductsUseCase;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> response = getAllProductsUseCase.execute();
        return ResponseEntity.ok(response);
    }
}
