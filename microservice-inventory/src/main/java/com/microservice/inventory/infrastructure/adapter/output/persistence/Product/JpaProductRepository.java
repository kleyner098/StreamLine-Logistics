package com.microservice.inventory.infrastructure.adapter.output.persistence.Product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la gestión de entidades de producto.
 * Proporciona métodos para acceder a la base de datos y realizar operaciones CRUD.
 */
@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findById(Long id);
    Optional<ProductEntity> findBySku(String sku);
}
