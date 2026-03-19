package com.microservice.order.application.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microservice.order.application.port.output.OrderItemRepository;
import com.microservice.order.application.port.output.OrderRepository;
import com.microservice.order.domain.model.Order;
import com.microservice.order.domain.model.OrderItem;

@ExtendWith(MockitoExtension.class)
public class CreateOrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private CreateOrderService createOrderService;

    @Test
    @DisplayName("Crear una nueva orden correctamente")
    void createOrderSuccessfully() {
        // Given
        Order order = new Order(null, "1L", 1L, null);
        OrderItem item1 = new OrderItem(null, 1L, 1L, 2, new BigDecimal("100"));
        order.addItem(item1);
        when(orderRepository.save(order)).thenReturn(1L);
        when(orderItemRepository.save(1L, item1)).thenReturn(2L);

        // When
        Long orderId = createOrderService.createOrder(order);

        // Then
        assertNotNull(orderId);
        assert(orderId.equals(1L));


    }
}
