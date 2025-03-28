package com.foursales.desafio.infra.elasticsearch.service;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchTemplateResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import com.foursales.desafio.infra.elasticsearch.models.ProductModel;
import co.elastic.clients.elasticsearch.core.SearchTemplateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductSeachService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public List<ProductModel> searchProducts(String name, Double minPrice, Double maxPrice, int from, int size) throws IOException, IOException {
        Map<String, JsonData> params = new HashMap<>();
        params.put("name", JsonData.of(name));
        params.put("min_price", JsonData.of(minPrice));
        params.put("max_price", JsonData.of(maxPrice));
        params.put("from", JsonData.of(from));
        params.put("size", JsonData.of(size));

        SearchTemplateRequest request = (new SearchTemplateRequest.Builder())
                .id("search-products")
                .params(params)
                .build();

        SearchTemplateResponse<ProductModel> response = elasticsearchClient.searchTemplate(
                request,
                ProductModel.class
        );

        List<ProductModel> productModels = new ArrayList<>();

        if (response.hits() != null && response.hits().hits() != null) {
            for (Hit<ProductModel> hit : response.hits().hits()) {
                ProductModel productModel = hit.source();
                if (productModel != null) {
                    productModels.add(productModel);
                }
            }
        }

        return productModels;
    }
}
