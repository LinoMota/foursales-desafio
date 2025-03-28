package com.foursales.desafio.adapters.controllers.product;

import com.foursales.desafio.adapters.controllers.dtos.product.CreateProductDTO;
import com.foursales.desafio.adapters.controllers.dtos.product.CreateProductResponseDTO;
import com.foursales.desafio.domain.usecases.product.CreateProductUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/products")
@Tag(name = "Products")
@SecurityRequirement(name = "bearer-key")
public class CreateProductController {

    @Autowired
    private CreateProductUseCase createProductUseCase;

    @PostMapping
    public ResponseEntity<CreateProductResponseDTO> createProduct(@RequestBody @Valid CreateProductDTO product) {
        CreateProductResponseDTO response = this.createProductUseCase.execute(product);
        return ResponseEntity.created(URI.create("/products/" + response.id()))
                .body(response);
    }

}
