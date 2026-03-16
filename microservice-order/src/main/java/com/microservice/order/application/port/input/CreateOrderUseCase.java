package com.microservice.order.application.port.input;

import com.microservice.order.domain.model.Order;

/**
 * Interfaz para el caso de uso de creación de orden.
 * Define el contrato para la creación de una orden, que incluye la información de la orden y sus items asociados.
 * Esta interfaz es implementada por el servicio de aplicación CreateOrderService, que se encarga
 */
public interface CreateOrderUseCase {

    
    public Long createOrder(Order order);
    
}
