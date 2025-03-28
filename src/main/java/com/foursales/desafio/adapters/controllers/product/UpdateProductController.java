package com.foursales.desafio.adapters.controllers.product;

import com.foursales.desafio.adapters.controllers.dtos.product.ProductDTO;
import com.foursales.desafio.adapters.controllers.dtos.product.UpdateProductDTO;
import com.foursales.desafio.domain.usecases.product.GetProductUseCase;
import com.foursales.desafio.domain.usecases.product.UpdateProductUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@Tag(name = "Products")
@SecurityRequirement(name = "bearer-key")
public class UpdateProductController {

    @Autowired
    private UpdateProductUseCase updateProductUseCase;

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody UpdateProductDTO product) {
        return ResponseEntity.ok(updateProductUseCase.execute(id, product));
    }

}
