package com.microservice.inventory.application.port.output;

import java.util.Optional;

import com.microservice.inventory.domain.model.Stock;

public interface StockRepository {
    Optional<Stock> findByProductId(Long productId);
    Long save(Long productId, String stock);
}
