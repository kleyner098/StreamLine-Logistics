package com.microservice.inventory.domain.valueobject;

import com.microservice.inventory.domain.exception.DomainException;

public record Sku(String value) {
    public Sku {
        if (value == null || value.isBlank()) {
            throw new DomainException("El SKU es obligatorio");
        }
    }
}
