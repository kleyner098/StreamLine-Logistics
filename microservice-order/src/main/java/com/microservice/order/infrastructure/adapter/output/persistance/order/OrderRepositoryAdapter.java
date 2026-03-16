package com.microservice.order.infrastructure.adapter.output.persistance.order;

import com.microservice.order.application.port.output.OrderRepository;
import com.microservice.order.domain.model.Order;

public class OrderRepositoryAdapter implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    OrderRepositoryAdapter(JpaOrderRepository jpaOrderRepository) {
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    public Long save(Order order) {
        
        OrderEntity orderEntity = OrderEntity.builder()
                .orderNumber(order.getOrderNumber())
                .customerId(order.getCustomerId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .build();

        orderEntity = jpaOrderRepository.save(orderEntity);

        return orderEntity.getId();
    }

}
