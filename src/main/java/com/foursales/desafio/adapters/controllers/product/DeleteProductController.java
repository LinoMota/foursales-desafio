package com.foursales.desafio.adapters.controllers.product;

import com.foursales.desafio.adapters.controllers.dtos.product.ProductDTO;
import com.foursales.desafio.domain.usecases.product.DeleteProductUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@Tag(name = "Products")
@SecurityRequirement(name = "bearer-key")
public class DeleteProductController {

    @Autowired
    private DeleteProductUseCase deleteProductUseCase;

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> getOneProduct(@PathVariable Long id) {
        return ResponseEntity.ok(deleteProductUseCase.execute(id));
    }

}
