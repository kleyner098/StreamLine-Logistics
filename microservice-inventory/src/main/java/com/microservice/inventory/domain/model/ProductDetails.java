package com.microservice.inventory.domain.model;

public record ProductDetails(Product product,
    Stock stock
) {
    public boolean isOutOfStock() {
        return stock == null || stock.available() <= 0;
    }
}
