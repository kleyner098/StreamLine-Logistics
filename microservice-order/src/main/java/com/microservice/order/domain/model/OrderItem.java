package com.microservice.order.domain.model;

import java.math.BigDecimal;

import com.microservice.order.domain.exception.DomainException;

/**
 * Clase de dominio que representa un ítem de una orden de compra.
 * Esta clase es inmutable, lo que significa que una vez creada, su estado no puede cambiar.
 */
public class OrderItem {

    private final Long id;
    private final Long orderId;
    private final Long productId;
    private final int quantity;
    private final BigDecimal priceAtPurchase;

    public OrderItem(Long id, Long orderId, Long productId, int quantity, BigDecimal priceAtPurchase) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }

    // Métodos de cambio de estado (Inmutabilidad)
    
    /**
     * Actualiza la cantidad de un ítem de la orden. Solo se puede actualizar si la orden está en estado PENDING.
     * @param newQuantity Nueva cantidad del ítem.
     * @return Nuevo ítem con la cantidad actualizada.
     */
    public OrderItem updateQuantity(int newQuantity) {
        return new OrderItem(this.id, this.orderId, this.productId, newQuantity, this.priceAtPurchase);
    }

    /**
     * Actualiza el precio de un ítem de la orden. Solo se puede actualizar si la orden está en estado PENDING.
     * @param newPrice Nuevo precio del ítem.
     * @return Nuevo ítem con el precio actualizado.
     */
    public OrderItem updatePrice(BigDecimal newPrice) {
        return new OrderItem(this.id, this.orderId, this.productId, this.quantity, newPrice);
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
