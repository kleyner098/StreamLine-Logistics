package com.microservice.inventory.infrastructure.adapter.output.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.microservice.inventory.domain.model.Product;
import com.microservice.inventory.domain.valueobject.Price;
import com.microservice.inventory.domain.valueobject.Sku;
import com.microservice.inventory.infrastructure.adapter.output.persistence.Product.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {

    @Mapping(target = "sku", source = "sku.value") // Extrae el String del record Sku
    @Mapping(target = "price", source = "price.amount") // Extrae el BigDecimal de Price
    ProductEntity toEntity(Product model);

    default Product toDomain(ProductEntity entity) {
        if (entity == null) return null;
        
        return new Product(
            entity.getId(),
            new Sku(entity.getSku()),
            entity.getName(),
            entity.getDescription(),
            new Price(entity.getPrice())
        );
    }
}
