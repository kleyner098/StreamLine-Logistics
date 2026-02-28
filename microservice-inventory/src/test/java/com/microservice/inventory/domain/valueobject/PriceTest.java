package com.microservice.inventory.domain.valueobject;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        DomainException exception = assertThrows(DomainException.class, 
            () -> new Price(new BigDecimal("-1.0")));   
        assertTrue(exception.getMessage().equals("El precio no puede ser nulo o 0"));
    }

    @Test
    @DisplayName("Crear un precio con un valor nulo")
    void createPriceWithNullValue() {
        DomainException exception = assertThrows(DomainException.class,
            () -> new Price(null));
        assertTrue(exception.getMessage().equals("El precio no puede ser nulo o 0"));
    }

    @Test
    @DisplayName("Crear un precio con más de 2 decimales")
    void createPriceWithMoreThanTwoDecimals() {
        DomainException exception = assertThrows(DomainException.class,
            () -> new Price(new BigDecimal("10.999")));
        assertTrue(exception.getMessage().equals("El precio no puede tener más de 2 decimales"));
    }
}
