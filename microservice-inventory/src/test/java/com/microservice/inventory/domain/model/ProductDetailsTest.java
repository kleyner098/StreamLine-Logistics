package com.microservice.inventory.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microservice.inventory.domain.exception.DomainException;
import com.microservice.inventory.domain.valueobject.Price;
import com.microservice.inventory.domain.valueobject.Sku;

public class ProductDetailsTest {

    private static Product product;
    private static Stock stock;

    @BeforeAll
    static void setUp() {
        product = new Product(1L, new Sku("SKU123"), "Product 1", "Description 1", new Price(new BigDecimal("190.99")));
        stock = new Stock(1L, 1L, 10, 2);
    }

    @Test
    @DisplayName("Crear ProductDetails con datos válidos")
    void createProductDetailsWithValidData() {
        ProductDetails productDetails = new ProductDetails(product, stock);
        
        assertTrue(productDetails.product().equals(product));
        assertTrue(productDetails.stock().equals(stock));
        assertTrue(productDetails.product().getId().equals(productDetails.stock().getProductId()));
    }

    @Test
    @DisplayName("Crear ProductDetails con producto nulo")
    void createProductDetailsWithNullProduct() {
        DomainException exception = assertThrows(DomainException.class, () -> new ProductDetails(null, stock));
        assertTrue(exception.getMessage().equals("El producto no puede ser nulo"));
    }

    @Test
    @DisplayName("Crear ProductDetails con stock nulo")
    void createProductDetailsWithNullStock() {
        DomainException exception = assertThrows(DomainException.class, () -> new ProductDetails(product, null));
        assertTrue(exception.getMessage().equals("El stock no puede ser nulo"));
    }

    @Test
    @DisplayName("Crear ProductDetails con producto y stock que no corresponden")
    void createProductDetailsWithMismatchedProductAndStock() {
        Stock mismatchedStock = new Stock(2L, 2L, 5, 1);
        DomainException exception = assertThrows(DomainException.class, () -> new ProductDetails(product, mismatchedStock));
        assertTrue(exception.getMessage().equals("El producto y el stock no corresponden al mismo producto"));
    }
    
    @Test
    @DisplayName("Stock no disponible")
    void checkStockNoAvailable() {
         Stock zeroStock = new Stock(2L, 1L, 0, 0);
        ProductDetails productDetails = new ProductDetails(product, zeroStock);
        assertTrue(productDetails.isOutOfStock() == true);
    }

    @Test
    @DisplayName("Stock disponible")
    void checkStockAvailable() {
        ProductDetails productDetails = new ProductDetails(product, stock);
        assertTrue(productDetails.isOutOfStock() == false);
    }
}