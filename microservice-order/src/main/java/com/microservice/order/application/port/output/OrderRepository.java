package com.microservice.order.application.port.output;

import com.microservice.order.domain.model.Order;

public interface OrderRepository {

    public Long save(Order order);

}
