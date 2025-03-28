package com.foursales.desafio.infra.elasticsearch.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.foursales.desafio.domain.entities.Product;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

@Data
@Document(indexName = "products")
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class ProductModel {
    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Double)
    private BigDecimal price;

    @Field(type = FieldType.Integer)
    private Integer stock;

    public ProductModel(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.stock = product.getStock();
    }

    @JsonCreator
    public ProductModel(
            @JsonProperty("name") String name,
            @JsonProperty("price") Double price,
            @JsonProperty("stock") Integer stock) {
        this.name = name;
        this.price = BigDecimal.valueOf(price);
        this.stock = stock;
    }

}
