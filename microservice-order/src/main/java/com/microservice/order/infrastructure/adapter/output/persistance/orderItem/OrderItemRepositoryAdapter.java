package com.microservice.order.infrastructure.adapter.output.persistance.orderItem;

import com.microservice.order.application.port.output.OrderItemRepository;
import com.microservice.order.domain.model.OrderItem;
import com.microservice.order.infrastructure.adapter.output.persistance.order.JpaOrderRepository;
import com.microservice.order.infrastructure.adapter.output.persistance.order.OrderEntity;

public class OrderItemRepositoryAdapter implements OrderItemRepository {

    private final JpaOrderItemRepository jpaOrderItemRepository;
    private final JpaOrderRepository jpaOrderRepository;

    OrderItemRepositoryAdapter(JpaOrderItemRepository jpaOrderItemRepository, JpaOrderRepository jpaOrderRepository) {
        this.jpaOrderItemRepository = jpaOrderItemRepository;
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    public Long save(Long orderId, OrderItem item) {

        // Creamos la referencia (proxy) del pedido sin hacer SELECT en la BD
        OrderEntity orderProxy = jpaOrderRepository.getReferenceById(orderId);

        OrderItemEntity orderItemEntity = OrderItemEntity.builder()
                .order(orderProxy)
                .productId(item.getProductId())
                .quantity(item.getQuantity())
                .priceAtPurchase(item.getPriceAtPurchase())
                .build();

        orderItemEntity = jpaOrderItemRepository.save(orderItemEntity);

        return orderItemEntity.getId();
    }

}
