package com.microservice.inventory.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microservice.inventory.domain.exception.DomainException;

public class StockTest {

    @Test
    @DisplayName("Crear un stock con valores válidos")
    void createStockWithValidValues() {
        Stock stock = new Stock(1L, 1L, 100, 20);

        assert stock.getId() == 1L;
        assert stock.getProductId() == 1L;
        assert stock.getTotalQuantity() == 100;
        assert stock.getTotalReserved() == 20;
        assert stock.available() == 80;
    }

    @Test
    @DisplayName("Crear un stock con cantidad total negativa")
    void createStockWithNegativeTotalQuantity() {
        assertThrows(DomainException.class, () -> new Stock(1L, 1L, -100, 20));
    }

    @Test
    @DisplayName("Crear un stock con cantidad reservada negativa")
    void createStockWithNegativeReservedQuantity() {
        assertThrows(DomainException.class, () -> new Stock(1L, 1L, 100, -20));
    }

    @Test
    @DisplayName("Crear un stock con cantidad reservada mayor que la cantidad total")
    void createStockWithReservedGreaterThanTotal() {
        assertThrows(DomainException.class, () -> new Stock(1L, 1L, 100, 120));
    }

    @Test
    @DisplayName("Agregar stock con cantidad válida")
    void addStockWithValidAmount() {
        Stock stock = new Stock(1L, 1L, 100, 20);
        Stock updatedStock = stock.addStock(50);

        assert updatedStock.getTotalQuantity() == 150;
        assert updatedStock.available() == 130;
    }

    @Test
    @DisplayName("Agregar stock con cantidad negativa")
    void addStockWithNegativeAmount() {
        Stock stock = new Stock(1L, 1L, 100, 20);
        assertThrows(DomainException.class, () -> stock.addStock(-50));
    }

    @Test
    @DisplayName("Reservar stock con cantidad válida")
    void reserveStockWithValidAmount() {
        Stock stock = new Stock(1L, 1L, 100, 20);        
        Stock updatedStock = stock.reserveStock(30);

        assert updatedStock.getTotalReserved() == 50;
        assert updatedStock.available() == 50;
    }

    @Test
    @DisplayName("Reservar stock con cantidad negativa")
    void reserveStockWithNegativeAmount() {
        Stock stock = new Stock(1L, 1L, 100, 20);
        assertThrows(DomainException.class, () -> stock.reserveStock(-30));
    }

    @Test
    @DisplayName("Reservar stock con cantidad mayor que el disponible")
    void reserveStockWithAmountGreaterThanAvailable() {
        Stock stock = new Stock(1L, 1L, 100, 20);
        assertThrows(DomainException.class, () -> stock.reserveStock(90));
    }

    @Test
    @DisplayName("Liberar stock con cantidad válida")
    void releaseStockWithValidAmount() {
        Stock stock = new Stock(1L, 1L, 100, 20);
        Stock updatedStock = stock.releaseStock(10);
        assert updatedStock.getTotalReserved() == 10;
        assert updatedStock.available() == 90;
    }

    @Test
    @DisplayName("Liberar stock con cantidad negativa")
    void releaseStockWithNegativeAmount() {
        Stock stock = new Stock(1L, 1L, 100, 20);
        assertThrows(DomainException.class, () -> stock.releaseStock(-10));
    }

    @Test
    @DisplayName("Liberar stock con cantidad mayor que el reservado")
    void releaseStockWithAmountGreaterThanReserved() {
        Stock stock = new Stock(1L, 1L, 100, 20);
        assertThrows(DomainException.class, () -> stock.releaseStock(30));
    }

    @Test
    @DisplayName("Confirmar reserva con cantidad válida")
    void confirmReservationWithValidAmount() {
        Stock stock = new Stock(1L, 1L, 100, 20);
        Stock updatedStock = stock.confirmReservation(10);
        assert updatedStock.getTotalQuantity() == 90;
        assert updatedStock.getTotalReserved() == 10;
        assert updatedStock.available() == 80;
    }

    @Test
    @DisplayName("Confirmar reserva con cantidad negativa")
    void confirmReservationWithNegativeAmount() {
        Stock stock = new Stock(1L, 1L, 100, 20);
        assertThrows(DomainException.class, () -> stock.confirmReservation(-10));
    }

    @Test
    @DisplayName("Confirmar reserva con cantidad mayor que el reservado")
    void confirmReservationWithAmountGreaterThanReserved() {
        Stock stock = new Stock(1L, 1L, 100, 20);
        assertThrows(DomainException.class, () -> stock.confirmReservation(30));
    }

    @Test
    @DisplayName("Confirmar reserva con cantidad mayor que la cantidad total")
    void confirmReservationWithAmountGreaterThanTotal() {
        Stock stock = new Stock(1L, 1L, 100, 20);
        assertThrows(DomainException.class, () -> stock.confirmReservation(110));   
    }
}
