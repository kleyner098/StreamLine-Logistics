package com.microservice.inventory.infrastructure.adapter.output.persistence.Stock;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repositorio JPA para la gestión de entidades de stock.
 * Proporciona métodos para acceder a la base de datos y realizar operaciones CRUD.
 */
@Repository
public interface JpaStockRepository extends JpaRepository<StockEntity, Long> {
    Optional<StockEntity> findByProductId(Long productId);
}
