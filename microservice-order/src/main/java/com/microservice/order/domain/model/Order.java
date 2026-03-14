package com.microservice.order.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.microservice.order.domain.exception.DomainException;


/**
 * Clase de dominio que representa una orden de compra. 
 * Esta clase es inmutable, lo que significa que una vez creada, su estado no puede cambiar. 
 * Cualquier operación que modifique el estado de la orden debe devolver una nueva instancia de Order.
 */
public class Order {

    private final Long id;
    private final String orderNumber;
    private final Long customerId;
    private final Status status;
    private final BigDecimal totalAmount;
    private final List<OrderItem> items = new ArrayList<>();


    public Order(Long id, String orderNumber, Long customerId, BigDecimal totalAmount) {
        
        validateOrderNumber(orderNumber);
        validateCustomerId(customerId);
        validateTotalAmount(totalAmount);

        this.id = id;
        this.orderNumber = orderNumber;
        this.customerId = customerId;
        this.status = Status.PENDING;
        this.totalAmount = totalAmount;
    }

    // Métodos de cambio de estado (Inmutabilidad)

    /**
     * Agrega un ítem a la orden. Solo se pueden agregar ítems si la orden está en estado PENDING.
     * @param item
     */
    public void addItem(OrderItem item) {
        if (this.status != Status.PENDING) {
            throw new IllegalStateException("No se pueden añadir ítems si el pedido no está pendiente.");
        }
        this.items.add(item);
    }

    /**
     * Elimina un ítem de la orden. Solo se pueden eliminar ítems si la orden está en estado PENDING.
     * @param item
     */
    public void removeItem(OrderItem item) {
        if (this.status != Status.PENDING) {
            throw new IllegalStateException("No se pueden eliminar ítems si el pedido no está pendiente.");
        }
        this.items.remove(item);
    }

    // Validaciones 

    void validateOrderNumber(String orderNumber) {
        if (orderNumber == null || orderNumber.isBlank()) {
            throw new DomainException("Número de orden inválido");
        }
    }

    void validateTotalAmount(BigDecimal totalAmount) {
        if (totalAmount == null || totalAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainException("Monto total inválido");
        }
    }

    void validateCustomerId(Long customerId) {
        if (customerId == null || customerId <= 0) {
            throw new DomainException("ID de cliente inválido");
        }
    }

    // Getters
    public Long getId() {return id;}
    public String getOrderNumber() {return orderNumber;}
    public Long getCustomerId() {return customerId;}
    public Status getStatus() {return status;}
    public BigDecimal getTotalAmount() {return totalAmount;}
    /**
     * Devuelve una lista inmutable de los ítems de la orden para preservar la inmutabilidad de la clase.
     * @return Lista inmutable de ítems de la orden.
     */
    public List<OrderItem> getItems() {return Collections.unmodifiableList(items);}
}
