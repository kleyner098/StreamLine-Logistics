package com.microservice.order.domain.exception;

public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }

}
