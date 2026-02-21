package com.microservice.inventory.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.microservice.inventory.application.dto.ProductCreateDto;
import com.microservice.inventory.infrastructure.adapter.output.persistence.Stock.StockEntity;

@Mapper(componentModel = "spring")
public interface StockDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productId", ignore = true) 
    @Mapping(target = "totalQuantity", source = "stock")
    @Mapping(target = "totalReserved", ignore = true)
    StockEntity toStockEntity(ProductCreateDto dto);
}
