package com.microservice.inventory.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.microservice.inventory.application.dto.ProductCreateDto;
import com.microservice.inventory.application.dto.ProductReponseDto;
import com.microservice.inventory.domain.model.ProductDetails;
import com.microservice.inventory.infrastructure.adapter.output.persistence.Product.ProductEntity;

@Mapper(componentModel = "spring", uses = { StockDtoMapper.class })
public interface ProductDtoMapper {

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "sku", source = "product.sku.value") 
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "description", source = "product.description")
    @Mapping(target = "price", source = "product.price.amount") // Extrae del Value Object
    //@Mapping(target = "availableStock", expression = "java(details.stock() != null ? details.stock().available() : 0)")
    @Mapping(target = "availableStock", source = "details") // Mapear directamente el objeto ProductDetails
    ProductReponseDto toResponse(ProductDetails details);

    @Mapping(target = "id", ignore = true)
    ProductEntity toEntity(ProductCreateDto createDto);

    //Si el stock existe, llama al método de negocio available() que se definio en la clase Stock
    default Integer mapAvailableStock(ProductDetails details) {
        if (details.stock() == null) return 0;
        return details.stock().available();
    }
}
