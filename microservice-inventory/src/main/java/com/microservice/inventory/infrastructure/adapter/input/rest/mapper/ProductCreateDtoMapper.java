package com.microservice.inventory.infrastructure.adapter.input.rest.mapper;

import org.mapstruct.Mapper;

import com.microservice.inventory.application.dto.ProductCreateDto;
import com.microservice.inventory.domain.model.Product;
import com.microservice.inventory.domain.valueobject.Price;
import com.microservice.inventory.domain.valueobject.Sku;

@Mapper(componentModel = "spring")
public interface ProductCreateDtoMapper {


    default Product toDomain(ProductCreateDto dto) {
        if (dto == null) return null;
        
        return new Product(
            null,
            new Sku(dto.getSku()),
            dto.getName(),
            dto.getDescription(),
            new Price(dto.getPrice())
        );
    }

}
