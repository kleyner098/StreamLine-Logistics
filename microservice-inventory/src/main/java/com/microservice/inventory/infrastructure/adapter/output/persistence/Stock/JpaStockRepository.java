package com.microservice.inventory.infrastructure.adapter.output.persistence.Stock;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaStockRepository extends JpaRepository<StockEntity, Long> {
    Optional<StockEntity> findByProductId(Long productId);
}
