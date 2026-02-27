package com.microservice.inventory.domain.valueobject;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.microservice.inventory.domain.exception.DomainException;

public class PriceTest {

    @Test
    @DisplayName("Crear un precio con un valor válido")
    void createPriceWithValidValue() {
        Price price = new Price(new java.math.BigDecimal("190.99"));
        assert price.amount().compareTo(new java.math.BigDecimal("190.99")) == 0;
    }

    @Test
    @DisplayName("Crear un precio con un valor negativo")
    void createPriceWithNegativeValue() {
        assertThrows(DomainException.class, 
            () -> new Price(new BigDecimal("-1.0")));   
    }

    @Test
    @DisplayName("Crear un precio con un valor nulo")
    void createPriceWithNullValue() {
        assertThrows(DomainException.class,
            () -> new Price(null));
    }
}
