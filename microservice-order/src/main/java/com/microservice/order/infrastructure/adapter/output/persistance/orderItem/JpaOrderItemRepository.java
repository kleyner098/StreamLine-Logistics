package com.microservice.order.infrastructure.adapter.output.persistance.orderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad OrderItem. 
 * Proporciona métodos CRUD y consultas personalizadas para gestionar los items de las órdenes en la base de datos.
 */
@Repository
public interface JpaOrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

}
