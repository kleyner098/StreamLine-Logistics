package com.microservice.order.application.port.output;

import com.microservice.order.domain.model.OrderItem;

public interface OrderItemRepository {

    public Long save(OrderItem item);

}
