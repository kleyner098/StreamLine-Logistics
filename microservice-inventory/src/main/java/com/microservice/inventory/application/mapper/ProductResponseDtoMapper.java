package com.microservice.inventory.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

import com.microservice.inventory.application.dto.ProductReponseDto;
import com.microservice.inventory.domain.model.ProductDetails;

@Mapper(componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
public interface ProductResponseDtoMapper {

    @Mapping(target = "sku", source = "product.sku.value") 
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "description", source = "product.description")
    @Mapping(target = "price", source = "product.price.amount") // Extrae del Value Object
    //@Mapping(target = "availableStock", expression = "java(details.stock() != null ? details.stock().available() : 0)")
    @Mapping(target = "availableStock", source = "details") // Mapear directamente el objeto ProductDetails
    ProductReponseDto toResponse(ProductDetails details);
    
    //Si el stock existe, llama al método de negocio available() que se definio en la clase Stock
    default Integer mapAvailableStock(ProductDetails details) {
        if (details.stock() == null) return 0;
        return details.stock().available();
    }

    List<ProductReponseDto> toResponseList(List<ProductDetails> detailsList);
}
