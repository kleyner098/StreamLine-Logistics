package com.microservice.inventory.domain.model;

import com.microservice.inventory.domain.exception.DomainException;


/**
 * Representa los detalles de un producto, incluyendo su información básica y su stock disponible.
 *
 * @param product La información básica del producto.
 * @param stock   El stock disponible del producto.
 */
public record ProductDetails(Product product,
    Stock stock
) {

    public ProductDetails {

        if (product != null && stock != null && !product.getId().equals(stock.getProductId())) {
            throw new DomainException("El producto y el stock no corresponden al mismo producto");
        }
    }

    public boolean isOutOfStock() {
        return stock == null || stock.available() <= 0;
    }
}
