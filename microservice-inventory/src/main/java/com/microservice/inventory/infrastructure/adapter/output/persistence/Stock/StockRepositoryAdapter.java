package com.microservice.inventory.infrastructure.adapter.output.persistence.Stock;

import org.springframework.stereotype.Component;

import com.microservice.inventory.application.port.output.StockRepository;
import com.microservice.inventory.infrastructure.adapter.output.persistence.mappers.StockEntityMapper;

@Component
public class StockRepositoryAdapter implements StockRepository{

    private final JpaStockRepository jpaStockRepository;
    private final StockEntityMapper stockEntityMapper;

    public StockRepositoryAdapter(JpaStockRepository jpaStockRepository, StockEntityMapper stockEntityMapper) {
        this.jpaStockRepository = jpaStockRepository;
        this.stockEntityMapper = stockEntityMapper;
    }

    @Override
    public java.util.Optional<com.microservice.inventory.domain.model.Stock> findByProductId(Long productId) {
        return jpaStockRepository.findByProductId(productId)
                .map(stockEntity -> stockEntityMapper.toDomain(stockEntity));
    }
}
