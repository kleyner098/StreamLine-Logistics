package com.microservice.inventory.application.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.microservice.inventory.application.dto.ProductReponseDto;
import com.microservice.inventory.domain.model.Product;
import com.microservice.inventory.domain.model.ProductDetails;
import com.microservice.inventory.domain.model.Stock;
import com.microservice.inventory.domain.valueobject.Price;
import com.microservice.inventory.domain.valueobject.Sku;

public class ProductResponseDtoMapperTest {

    private ProductResponseDtoMapper mapper;


    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(ProductResponseDtoMapper.class);
    }


    @Test
    @DisplayName("Mappear ProductDetails a ProductResponseDto")
    void MapProductDetailsToResponseDto(){
        //Given
        Product product = new Product(1L, new Sku("SKU123"), "Producto test", null, new Price(new BigDecimal("10.99")));
        Stock stock = new Stock(1L, product.getId(), 100, 10);
        ProductDetails details = new ProductDetails(product, stock);

        //When
        ProductReponseDto result = mapper.toResponse(details);

        // Then
        assertEquals(product.getSku().value(), result.getSku());
        assertEquals(product.getPrice().amount(), result.getPrice());
        assertEquals( stock.available(), result.getAvailableStock());
    }

    @Test
    @DisplayName("Mappear ProductDetails con stock nulo a ProductResponseDto")
    void MapProductDetailsWithNullStockToResponseDto(){
        //Given
        Product product = new Product(1L, new Sku("SKU123"), "Producto test", null, new Price(new BigDecimal("10.99")));
        ProductDetails details = new ProductDetails(product, null);

        //When
        ProductReponseDto result = mapper.toResponse(details);
        // Then
        assertEquals(product.getSku().value(), result.getSku());
        assertEquals(product.getPrice().amount(), result.getPrice());
        assertEquals(0, result.getAvailableStock());
    }
}
