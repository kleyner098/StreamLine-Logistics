package com.microservice.inventory.domain.model;

import com.microservice.inventory.domain.exception.DomainException;

public record ProductDetails(Product product,
    Stock stock
) {

    public ProductDetails {
        if (product == null) {
            throw new DomainException("El producto no puede ser nulo");
        }
        if (stock == null) {
            throw new DomainException("El stock no puede ser nulo");
        }

        if (!product.getId().equals(stock.getProductId())) {
            throw new DomainException("El producto y el stock no corresponden al mismo producto");
        }
    }

    public boolean isOutOfStock() {
        return stock == null || stock.available() <= 0;
    }
}
