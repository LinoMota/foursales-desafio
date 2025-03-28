package com.foursales.desafio.adapters.controllers.product;

import com.foursales.desafio.adapters.controllers.dtos.product.ProductDTO;
import com.foursales.desafio.domain.usecases.product.GetProductUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@Tag(name = "Products")
@SecurityRequirement(name = "bearer-key")
public class GetProductController {

    @Autowired
    private GetProductUseCase getProductUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getOneProduct(@PathVariable Long id) {
        return ResponseEntity.ok(getProductUseCase.execute(id));
    }

}
