package com.microservice.order.application.port.input;

import com.microservice.order.domain.model.Order;

public interface CreateOrderUseCase {

    
    public Long createOrder(Order order);
    
}
