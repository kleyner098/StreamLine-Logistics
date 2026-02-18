package com.microservice.inventory.infrastructure.adapter.output.persistence.Stock;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface JpaStockRepository {
    Optional<StockEntity> findByProductId(Long productId);
}
