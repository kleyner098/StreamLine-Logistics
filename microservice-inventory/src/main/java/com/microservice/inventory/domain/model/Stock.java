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
        validateReserved(totalReserved, totalQuantity);

        this.id = id;
        this.productId = productId;
        this.totalQuantity = totalQuantity;
        this.totalReserved = totalReserved;
    }

    // Métodos de cambio de estado

    /**
     * Agrega una cantidad de stock al stock total del producto. La cantidad agregada no puede ser negativa.
     * @param amount
     * @return Una nueva instancia de Stock con la cantidad total actualizada. Si la cantidad agregada es negativa, se lanza una excepción.
     */
    public Stock addStock(int amount) {
        validateQuantity(amount);
        return new Stock(this.id, this.productId, this.totalQuantity + amount, this.totalReserved);
    }

    /**
     * Confirma una reserva de stock, reduciendo tanto la cantidad total como la cantidad reservada en la cantidad especificada.
     * @param amount
     * @return Una nueva instancia de Stock con la cantidad total y la cantidad reservada actualizadas. Si no hay suficiente stock reservado para confirmar, se lanza una excepción.
     */
    public Stock confirmReservation(int amount) {
        if(totalQuantity == 0) throw new DomainException("No hay stock disponible para confirmar la reserva.");
        if(totalReserved == 0) throw new DomainException("No hay stock reservado para confirmar.");
        if (amount > totalReserved) throw new DomainException("No hay suficiente stock reservado para reducir. Stock reservado: " + totalReserved);
        validateReserved(amount, totalQuantity);
        validateQuantity(amount);
        return new Stock(this.id, this.productId, this.totalQuantity - amount, this.totalReserved - amount);
    }

    /**
     * Reserva una cantidad de stock disponible para un pedido.
     * @param amount
     * @return Una nueva instancia de Stock con la cantidad reservada actualizada. Si no hay suficiente stock disponible, se lanza una excepción.
     */
    public Stock reserveStock(int amount) {
        if (amount > available()) throw new DomainException("No hay suficiente stock disponible para reservar. Stock disponible: " + available());
        validateReserved(amount, totalQuantity);
        return new Stock(this.id, this.productId, this.totalQuantity, this.totalReserved + amount);
    }

    /**
     * Libera una cantidad de stock reservada.
     * @param amount
     * @return Una nueva instancia de Stock con la cantidad reservada actualizada. Si no hay suficiente stock reservado, se lanza una excepción.
     */
    public Stock releaseStock(int amount) {
        if(totalReserved == 0) throw new DomainException("No hay stock reservado para liberar.");
        if (amount > totalReserved) throw new DomainException("No hay suficiente stock reservado para liberar. Stock reservado: " + totalReserved);
        validateReserved(amount, totalQuantity);
        return new Stock(this.id, this.productId, this.totalQuantity, this.totalReserved - amount);
    }
    

    //Validaciones
    /*private void validateProduct(Long product) {
        if (product == null) throw new DomainException("El producto es obligatorio");
    }*/

    private void validateQuantity(int quantity) {
        if (quantity < 0) throw new DomainException("La cantidad no puede ser negativa");
    }

    private void validateReserved(int reserved, int totalQuantity) {
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
