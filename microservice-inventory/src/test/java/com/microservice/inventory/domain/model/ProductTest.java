package com.microservice.inventory.domain.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microservice.inventory.domain.exception.DomainException;
import com.microservice.inventory.domain.valueobject.Price;
import com.microservice.inventory.domain.valueobject.Sku;

public class ProductTest {

    @Test
    @DisplayName("Crear un producto con datos válidos")
    void createProductWithValidData() {
    
        Product product = new Product(1L, 
            new Sku("SKU123"), 
            "Producto de prueba", 
            "Descripción del producto", 
            new Price(new BigDecimal("190.99")));

        assert product != null;
        assert product.getId() == 1L;
        assert product.getSku().value().equals("SKU123");
        assert product.getName().equals("Producto de prueba");
        assert product.getDescription().equals("Descripción del producto");
        assert product.getPrice().amount().compareTo(new BigDecimal("190.99")) == 0;       
    }

    @Test
    @DisplayName("Crear un producto sin nombre")
    void createProductWitoutName() {
        assertThrows( DomainException.class, 
            () -> new Product(1L, 
                new Sku("SKU123"), 
                null, 
                "Descripción del producto", 
                new Price(new BigDecimal("190.99"))));
    }

    @Test
    @DisplayName("Actualizar el nombre de un producto")
    void updateProductName() {
        Product product = new Product(1L, 
            new Sku("SKU123"), 
            "Producto de prueba", 
            "Descripción del producto", 
            new Price(new BigDecimal("190.99")));

        Product updatedProduct = product.updateName("Producto actualizado");

        assert updatedProduct.getName().equals("Producto actualizado");
        assertFalse(product == updatedProduct);
    }

}
