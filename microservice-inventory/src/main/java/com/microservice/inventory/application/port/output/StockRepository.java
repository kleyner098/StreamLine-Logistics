package com.microservice.inventory.application.port.output;

import java.util.Optional;

import com.microservice.inventory.domain.model.Stock;

/**
 * Repositorio para el acceso a los datos de stock.
 * Define los métodos para interactuar con la capa de persistencia, como buscar el stock por ID de producto, 
 * guardar o actualizar el stock, etc.
 */
public interface StockRepository {

    /**
     * Busca el stock de un producto por su ID.
     * @param productId
     * @return Un Optional que contiene el stock si se encuentra, o vacío si no existe.
     */
    Optional<Stock> findByProductId(Long productId);

    /**
     * Guarda o actualiza el stock de un producto.
     * @param productId
     * @param stock
     * @return El stock guardado o actualizado.
     */
    Stock save(Long productId, Stock stock);

    void update(Stock stock);
}
