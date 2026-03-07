package com.microservice.inventory.application.service;

import com.microservice.inventory.application.exception.ApplicationException;
import com.microservice.inventory.application.port.input.UpdateStockUseCase;
import com.microservice.inventory.application.port.output.StockRepository;
import com.microservice.inventory.domain.model.Stock;

public class UpdateStockService implements UpdateStockUseCase {

    private final StockRepository stockRepository;

    public UpdateStockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    /**
     * Agrega stock a un producto específico.
     *
     * @param productId El ID del producto.
     * @param quantity La cantidad de stock a agregar.
     */
    @Override
    public void addStock(Long productId, int quantity) {

        Stock stock = stockRepository.findByProductId(productId).orElseThrow(
            () -> new ApplicationException("Stock no encontrado con ID: " + productId));

        stock = stock.addStock(quantity);
        stockRepository.update(stock);

     }

    /**
     * Reserva stock para un producto específico.
     *
     * @param productId El ID del producto.
     * @param quantity La cantidad de stock a reservar.
     */
    @Override
    public void reserveStock(Long productId, int quantity) {
        Stock stock = stockRepository.findByProductId(productId).orElseThrow(
            () -> new ApplicationException("Stock no encontrado con ID: " + productId));

        stock = stock.reserveStock(quantity);
        stockRepository.update(stock);
    }

    /**
     * Libera stock reservado para un producto específico.
     *
     * @param productId El ID del producto.
     * @param quantity La cantidad de stock a liberar.
     */
    @Override
    public void releaseStock(Long productId, int quantity) {
        Stock stock = stockRepository.findByProductId(productId).orElseThrow(
            () -> new ApplicationException("Stock no encontrado con ID: " + productId));

        stock = stock.releaseStock(quantity);
        stockRepository.update(stock);
    }

    /**
     * Consume stock reservado para un producto específico.
     *
     * @param productId El ID del producto.
     * @param quantity La cantidad de stock a consumir.
     */
    @Override
    public void consumeStock(Long productId, int quantity) {
        Stock stock = stockRepository.findByProductId(productId).orElseThrow(
            () -> new ApplicationException("Stock no encontrado con ID: " + productId));

        stock = stock.confirmReservation(quantity);
        stockRepository.update(stock);
    }

}
