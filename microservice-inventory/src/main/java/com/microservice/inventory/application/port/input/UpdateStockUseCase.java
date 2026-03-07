package com.microservice.inventory.application.port.input;

/**
 * Interfaz para el caso de uso de actualización de stock.
 * Define los métodos para modificar el stock de productos en el sistema, incluyendo agregar, reservar, liberar y consumir stock.
 */
public interface UpdateStockUseCase {

    /**
     * Agrega stock a un producto.
     * @param productId El ID del producto.
     * @param quantity La cantidad de stock a agregar.
     */
    void addStock(Long productId, int quantity);

    /**
     * Reserva stock para un producto.
     * @param productId El ID del producto.
     * @param quantity La cantidad de stock a reservar.
     */
    void reserveStock(Long productId, int quantity);

    /**
     * Libera stock reservado para un producto.
     * @param productId El ID del producto.
     * @param quantity La cantidad de stock a liberar.
     */
    void releaseStock(Long productId, int quantity);

    /**
     * Consume stock de un producto.
     * @param productId El ID del producto.
     * @param quantity La cantidad de stock a consumir.
     */
    void consumeStock(Long productId, int quantity);

}
