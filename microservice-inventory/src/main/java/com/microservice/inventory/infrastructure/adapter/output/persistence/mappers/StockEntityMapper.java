package com.microservice.inventory.infrastructure.adapter.output.persistence.mappers;

import org.mapstruct.Mapper;

import com.microservice.inventory.domain.model.Stock;
import com.microservice.inventory.infrastructure.adapter.output.persistence.Stock.StockEntity;

@Mapper(componentModel = "spring")
public interface StockEntityMapper {
 
    StockEntity toEntity(Stock model);

    default Stock toDomain(StockEntity entity) {
        if (entity == null) return null;
        
        return new Stock(
            entity.getId(),
            entity.getProductId(),
            entity.getTotalQuantity(),
            entity.getTotalReserved()
        );
    }
}
