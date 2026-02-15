package com.microservice.inventory.domain.entity;

import java.math.BigDecimal;

import com.microservice.inventory.domain.exception.DomainException;

public class Product {

    private final Long id;
    private final String sku;
    private final String name;
    private final String description;
    private final BigDecimal price;

    Product( Long id, String sku, String name, String description, BigDecimal price) {
        this.id = id;
        this.sku = sku;

        if (name == null || name.isBlank() || name.isEmpty()) {
            throw new DomainException("El nombre no puede ser nulo, vacío o en blanco");
        }
        this.name = name;

        this.description = description;

        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainException("El precio no puede ser nulo o 0");
        }
        if (price.scale() > 2) {
            throw new DomainException("El precio no puede tener más de 2 decimales");
        }

        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
    
    

}
