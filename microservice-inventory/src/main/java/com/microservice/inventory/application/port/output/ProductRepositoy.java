package com.microservice.inventory.application.port.output;

import java.util.Optional;

import com.microservice.inventory.domain.model.Product;

public interface ProductRepositoy {
    Optional<Product> findById(Long id);
}
