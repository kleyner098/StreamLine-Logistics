package com.microservice.order.domain.model;

import java.math.BigDecimal;

import com.microservice.order.domain.exception.DomainException;

public class Order {

    private final Long id;
    private final String orderNumber;
    private final Long customerId;
    private final Status status;
    private final BigDecimal totalAmount;


    public Order(Long id, String orderNumber, Long customerId, Status status, BigDecimal totalAmount) {
        
        validateOrderNumber(orderNumber);
        validateCustomerId(customerId);
        validateTotalAmount(totalAmount);
        
        this.id = id;
        this.orderNumber = orderNumber;
        this.customerId = customerId;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    // Métodos de cambio de estado (Inmutabilidad)

    public Order updateStatus(Status newStatus) {
        return new Order(this.id, this.orderNumber, this.customerId, newStatus, this.totalAmount);
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
    
}
