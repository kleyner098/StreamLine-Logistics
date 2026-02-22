package com.microservice.inventory.domain.model;


import com.microservice.inventory.domain.exception.DomainException;

/**
 * Representa el stock de un producto en el sistema de inventario.
 * El stock tiene una cantidad total y una cantidad reservada.
 * La cantidad disponible se calcula como la diferencia entre la cantidad total y la cantidad reservada.
 * La cantidad total y la cantidad reservada no pueden ser negativas, y la cantidad reservada no puede ser mayor que la cantidad total.
 */
public class Stock {
    
    private final Long id;
    private final Long productId;
    private final int totalQuantity;
    private final int totalReserved;

    public Stock(Long id, Long productId, int totalQuantity, int totalReserved) {

        // validateProduct(productId);
        validateQuantity(totalQuantity);
        validateReserved(totalReserved);

        this.id = id;
        this.productId = productId;
        this.totalQuantity = totalQuantity;
        this.totalReserved = totalReserved;
    }

    // Métodos de cambio de estado

    public Stock addStock(int amount) {
        validateQuantity(amount);
        return new Stock(this.id, this.productId, this.totalQuantity + amount, this.totalReserved);
    }

    public Stock confirmReservation(int amount) {
        if (amount > totalReserved) throw new DomainException("No hay suficiente stock reservado para reducir. Stock reservado: " + totalReserved);
        validateReserved(amount);
        validateQuantity(amount);
        return new Stock(this.id, this.productId, this.totalQuantity - amount, this.totalReserved - amount);
    }

    /**
     * Reserva una cantidad de stock disponible para un pedido.
     * @param amount
     * @return
     */
    public Stock reserveStock(int amount) {
        if (amount > available()) throw new DomainException("No hay suficiente stock disponible para reservar. Stock disponible: " + available());
        validateReserved(amount);
        return new Stock(this.id, this.productId, this.totalQuantity, this.totalReserved + amount);
    }

    /**
     * Libera una cantidad de stock reservada.
     * @param amount
     * @return
     */
    public Stock releaseStock(int amount) {
        if (amount > totalReserved) throw new DomainException("No hay suficiente stock reservado para liberar. Stock reservado: " + totalReserved);
        validateReserved(amount);
        return new Stock(this.id, this.productId, this.totalQuantity, this.totalReserved - amount);
    }
    

    //Validaciones
    private void validateProduct(Long product) {
        if (product == null) throw new DomainException("El producto es obligatorio");
    }

    private void validateQuantity(int quantity) {
        if (quantity < 0) throw new DomainException("La cantidad no puede ser negativa");
    }

    private void validateReserved(int reserved) {
        if (reserved < 0) throw new DomainException("La cantidad reservada no puede ser negativa");
        if (reserved > totalQuantity) throw new DomainException("La cantidad reservada no puede ser mayor que la cantidad total");
    }

    //Getters
    public Long getId() {return id;}
    public Long getProductId() {return productId;}
    public int getTotalQuantity() {return totalQuantity;}
    public int getTotalReserved() {return totalReserved;}
    public int available() {return totalQuantity - totalReserved;}
    
}
