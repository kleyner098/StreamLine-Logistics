package com.microservice.inventory.domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microservice.inventory.domain.exception.DomainException;

public class SkuTest {

    @Test
    @DisplayName("Crear un SKU con un valor válido")
    void createSkuWithValidValue() {
        Sku sku = new Sku("ABC123");
        assert sku.value().equals("ABC123");
    }

    @Test
    @DisplayName("Crear un SKU con un valor nulo")
    void createSkuWithNullValue() {
        assertThrows(DomainException.class,
            () -> new Sku(null));
    }

}
