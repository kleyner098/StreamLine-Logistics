package com.microservice.order.infrastructure.adapter.output.persistance.orderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.order.domain.model.OrderItem;

@Repository
public interface JpaOrderItemRepository extends JpaRepository<OrderItem, Long> {

}
