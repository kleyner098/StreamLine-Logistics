package com.microservice.order.domain.model;

import java.math.BigDecimal;

import com.microservice.order.domain.exception.DomainException;

public class OderItem {

    private final Long id;
    private final Long orderId;
    private final Long productId;
    private final int quantity;
    private final BigDecimal priceAtPurchase;

    public OderItem(Long id, Long orderId, Long productId, int quantity, BigDecimal priceAtPurchase) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }

    // Métodos de cambio de estado (Inmutabilidad)
    public OderItem updateQuantity(int newQuantity) {
        return new OderItem(this.id, this.orderId, this.productId, newQuantity, this.priceAtPurchase);
    }

    public OderItem updatePrice(BigDecimal newPrice) {
        return new OderItem(this.id, this.orderId, this.productId, this.quantity, newPrice);
    }

    // Validaciones
    void validateOrderId(Long orderId) {
        if (orderId == null || orderId <= 0) {
            throw new DomainException("ID de orden inválido");
        }
    }

    void validateProductId(Long productId) {
        if (productId == null || productId <= 0) {
            throw new DomainException("ID de producto inválido");
        }
    }

    void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new DomainException("Cantidad debe ser mayor a cero");
        }
    }

    void validatePrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainException("Precio inválido");
        }
    }

    // Getters
    public Long getId() {return id;}
    public Long getOrderId() {return orderId;}
    public Long getProductId() {return productId;}
    public int getQuantity() {return quantity;}
    public BigDecimal getPriceAtPurchase() {return priceAtPurchase;}  
}
