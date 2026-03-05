package com.microservice.inventory.domain.valueobject;

import com.microservice.inventory.domain.exception.DomainException;

/**
 * Representa el SKU (Stock Keeping Unit) de un producto.
 */
public record Sku(String value) {
    public Sku {
        if (value == null || value.isBlank()) {
            throw new DomainException("El SKU es obligatorio");
        }
    }
}
