package com.microservice.order.application.port.input;

import com.microservice.order.domain.model.OrderItem;

public interface CreateOrderUseCase {

    
    public Long createOrder(String customerId, OrderItem item);
    
}
