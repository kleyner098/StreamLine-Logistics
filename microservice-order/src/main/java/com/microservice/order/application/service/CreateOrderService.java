package com.microservice.order.application.service;

import com.microservice.order.application.port.input.CreateOrderUseCase;
import com.microservice.order.application.port.output.OrderItemRepository;
import com.microservice.order.application.port.output.OrderRepository;
import com.microservice.order.domain.model.Order;


/**
 * Servicio de aplicación para crear una orden. Este servicio se encarga de coordinar la creación de una orden y sus items asociados.
 * Implementa el caso de uso de creación de orden definido en la interfaz CreateOrderUseCase. 
 * Utiliza los repositorios de orden y item de orden para persistir la información en la base
 */
public class CreateOrderService implements CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public CreateOrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Long createOrder(Order order) {
        
        Long orderId = orderRepository.save(order);
        order.getItems().forEach(item -> {
            orderItemRepository.save(orderId, item);
        });

        return orderId;
    }

}
