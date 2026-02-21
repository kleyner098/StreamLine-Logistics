package com.microservice.inventory.application.port.output;

import java.util.Optional;

import com.microservice.inventory.domain.model.Product;

public interface ProductRepository {
    Optional<Product> findById(Long id);
    Optional<Product> findBySku(String sku);
    Long save(String sku, String name, String description, Double price);
}
