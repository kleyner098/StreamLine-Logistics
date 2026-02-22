package com.microservice.inventory.infrastructure.adapter.output.persistence.Product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findById(Long id);
    Optional<ProductEntity> findBySku(String sku);
}
