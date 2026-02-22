package com.microservice.inventory.domain.valueobject;

import java.math.BigDecimal;

public record Price(BigDecimal amount) {
    public Price {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio no puede ser nulo o 0");
        }
        if (amount.scale() > 2) {
            throw new IllegalArgumentException("El precio no puede tener más de 2 decimales");
        }
    }

}
