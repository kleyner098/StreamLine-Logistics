package com.microservice.order.domain.model;

/**
 * Enumeración que representa los posibles estados de una orden.
 */
public enum Status {

    PENDING,
    CONFIRMED,
    SHIPPED,
    CANCELED,
}
