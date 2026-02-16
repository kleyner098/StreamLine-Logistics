package com.microservice.inventory.domain.model;

import java.math.BigDecimal;

public record Price(BigDecimal value) {
    public Price {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio no puede ser nulo o 0");
        }
        if (value.scale() > 2) {
            throw new IllegalArgumentException("El precio no puede tener más de 2 decimales");
        }
    }

}
