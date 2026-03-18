package com.microservice.order.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microservice.order.domain.model.OrderItem;

public class OrderItemTest {

    @Test
    @DisplayName("Crear un ItemOrder con datos válidos")
    public void createOrderItemWithValidData() {
        OrderItem item = new OrderItem(1L, 1L, 1L, 2, new BigDecimal("50.00"));

        assert item != null;
        assert item.getId() == 1L;
        assert item.getOrderId() == 1L;
        assert item.getProductId() == 1L;
        assert item.getQuantity() == 2;
        assert item.getPriceAtPurchase().compareTo(new BigDecimal("50.00")) == 0;
    }

    @Test
    @DisplayName("Actualizar la cantidad de un ítem de la orden")
    public void updateOrderItemQuantity() {
        OrderItem item = new OrderItem(1L, 1L, 1L, 2, new BigDecimal("50.00"));

        OrderItem updatedItem = item.updateQuantity(5);

        assertEquals(updatedItem.getQuantity(), 5);
        assertTrue(item != updatedItem);
    }

    @Test
    @DisplayName("Actualizar el precio de un ítem de la orden")
    public void updateOrderItemPrice() {
        OrderItem item = new OrderItem(1L, 1L, 1L, 2, new BigDecimal("50.00"));
        
        OrderItem updatedItem = item.updatePrice(new BigDecimal("45.00"));
        
        assertEquals(updatedItem.getPriceAtPurchase().compareTo(new BigDecimal("45.00")), 0);
        assertTrue(item != updatedItem);
    }

}
