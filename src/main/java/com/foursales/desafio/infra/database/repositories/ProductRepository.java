package com.foursales.desafio.infra.database.repositories;

import com.foursales.desafio.adapters.controllers.dtos.queries.TopUserPurchasesDTO;
import com.foursales.desafio.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
