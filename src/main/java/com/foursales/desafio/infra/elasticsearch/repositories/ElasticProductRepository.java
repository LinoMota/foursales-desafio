package com.foursales.desafio.infra.elasticsearch.repositories;

import com.foursales.desafio.infra.elasticsearch.models.ProductModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticProductRepository extends ElasticsearchRepository<ProductModel, Long> {
}
