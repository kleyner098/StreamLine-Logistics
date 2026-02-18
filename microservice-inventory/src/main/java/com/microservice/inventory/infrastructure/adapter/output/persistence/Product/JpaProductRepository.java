package com.microservice.inventory.infrastructure.adapter.output.persistence.Product;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository {
    Optional<ProductEntity> findById(Long id);
}
