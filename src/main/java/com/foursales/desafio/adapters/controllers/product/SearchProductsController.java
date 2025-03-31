package com.foursales.desafio.adapters.controllers.product;

import com.foursales.desafio.adapters.controllers.dtos.product.ProductDTO;
import com.foursales.desafio.domain.enums.Category;
import com.foursales.desafio.domain.usecases.product.GetProductUseCase;
import com.foursales.desafio.infra.elasticsearch.models.ProductModel;
import com.foursales.desafio.infra.elasticsearch.service.ProductSeachService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/search")
@Tag(name = "Products")
@SecurityRequirement(name = "bearer-key")
public class SearchProductsController {

    @Autowired
    private ProductSeachService productSeachService;

    @PostMapping
    public List<ProductModel> searchProducts(
            @RequestParam(required = true) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int from,
            @RequestParam(defaultValue = "10") int size
    ) throws IOException {
        Double minPriceValue = (minPrice != null) ? minPrice : Double.MIN_VALUE;
        Double maxPriceValue = (maxPrice != null) ? maxPrice : Double.MAX_VALUE;

        List<ProductModel> products = productSeachService.searchProducts(name, category, minPriceValue, maxPriceValue, from, size);
        return ResponseEntity.ok(products).getBody();
    }

}
