package com.microservice.order.infrastructure.adapter.output.persistance.orderItem;

import com.microservice.order.application.port.output.OrderItemRepository;
import com.microservice.order.domain.model.OrderItem;

public class OrderItemRepositoryAdapter implements OrderItemRepository {

    private final JpaOrderItemRepository jpaOrderItemRepository;

    OrderItemRepositoryAdapter(JpaOrderItemRepository jpaOrderItemRepository) {
        this.jpaOrderItemRepository = jpaOrderItemRepository;
    }

    @Override
    public Long save(Long orderId, OrderItem item) {

        OrderItemEntity orderItemEntity = OrderItemEntity.builder()
                .orderId(orderId)
                .productId(item.getProductId())
                .quantity(item.getQuantity())
                .priceAtPurchase(item.getPriceAtPurchase())
                .build();

        orderItemEntity = jpaOrderItemRepository.save(orderItemEntity);

        return orderItemEntity.getId();
    }

}
