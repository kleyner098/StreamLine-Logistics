package com.microservice.order.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microservice.order.domain.exception.DomainException;
import com.microservice.order.domain.model.Order;
import com.microservice.order.domain.model.OrderItem;
import com.microservice.order.domain.model.Status;

public class OrderTest {

    @Test
    @DisplayName("Crear una orden con datos válidos")
    void createOrderWithValidData() {
        Order order = new Order(1L, "ORD123", 1L, new BigDecimal("100.00"));

        assert order != null;
        assert order.getId() == 1L;
        assert order.getOrderNumber().equals("ORD123");
        assert order.getCustomerId() == 1L;
        assert order.getStatus() == Status.PENDING;
        assert order.getTotalAmount().compareTo(new BigDecimal("100.00")) == 0;       
    }

    @Test
    @DisplayName("Crear una orden sin número de orden")
    void createOrderWithNullOrderNumber() {
        DomainException exception = assertThrows( DomainException.class, 
            () -> new Order(1L, null, 1L, new BigDecimal("100.00")));
        assert exception.getMessage().equals("Número de orden inválido");
    }

    @Test
    @DisplayName("Crear una orden sin id de cliente")
    void createOrderWithNullCustomerId() {
        DomainException exception = assertThrows( DomainException.class, 
            () -> new Order(1L, "ORD123", null, new BigDecimal("100.00")));
        assert exception.getMessage().equals("ID de cliente inválido");
    }

    @Test
    @DisplayName("Crear una orden con monto total negativo")
    void createOrderWithNegativeTotalAmount() {
        DomainException exception = assertThrows( DomainException.class, 
            () -> new Order(1L, "ORD123", 1L, new BigDecimal("-100.00")));
        assert exception.getMessage().equals("Monto total inválido");
    }

    @Test
    @DisplayName("Agregar un ítem a una orden en estado PENDING")
    void addItemToPendingOrder() {
        Order order = new Order(1L, "ORD123", 1L, new BigDecimal("100.00"));
        OrderItem item = new OrderItem(1L, 1L, 1L, 2, new BigDecimal("50.00"));

        order.addItem(item);

        assertTrue(order.getItems().size() == 1);
    }

    @Test
    @DisplayName("Remover un ítem a una orden en estado PENDING")
    void removeItemFromPendingOrder() {
        Order order = new Order(1L, "ORD123", 1L, new BigDecimal("100.00"));
        OrderItem item = new OrderItem(1L, 1L, 1L, 2, new BigDecimal("50.00"));

        order.addItem(item);
        order.removeItem(item);

        assertTrue(order.getItems().size() == 0);
        
    }
}
