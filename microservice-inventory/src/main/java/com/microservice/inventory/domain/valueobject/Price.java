package com.microservice.inventory.domain.valueobject;

import java.math.BigDecimal;

import com.microservice.inventory.domain.exception.DomainException;

public record Price(BigDecimal amount) {
    public Price {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainException("El precio no puede ser nulo o 0");
        }
        if (amount.scale() > 2) {
            throw new DomainException("El precio no puede tener más de 2 decimales");
        }
    }

}
