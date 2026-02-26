package com.microservice.inventory.application.port.output;

import java.util.List;
import java.util.Optional;

import com.microservice.inventory.domain.model.Product;

public interface ProductRepository {

    List<Product> findAll();
    Optional<Product> findById(Long id);
    Optional<Product> findBySku(String sku);
    Product save(Product product);
    
}
