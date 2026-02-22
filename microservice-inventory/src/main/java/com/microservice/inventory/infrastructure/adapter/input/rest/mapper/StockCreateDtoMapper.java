package com.microservice.inventory.infrastructure.adapter.input.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.microservice.inventory.application.dto.ProductCreateDto;
import com.microservice.inventory.domain.model.Stock;


@Mapper(componentModel = "spring")
public interface StockCreateDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "totalReserved", ignore = true  )
    @Mapping(target = "totalQuantity", source = "stock")
    @Mapping(target = "confirmReservation", ignore = true)
    @Mapping(target = "releaseStock", ignore = true)
    @Mapping(target = "reserveStock", ignore = true)
    Stock toDomain(ProductCreateDto productCreateDto);
    
}