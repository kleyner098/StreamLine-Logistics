package com.microservice.inventory.infrastructure.adapter.output.persistence.Stock;

import org.springframework.stereotype.Component;

import com.microservice.inventory.application.port.output.StockRepository;
import com.microservice.inventory.domain.model.Stock;
import com.microservice.inventory.infrastructure.adapter.output.persistence.mappers.StockEntityMapper;


/**
 * Adaptador de repositorio para la gestión de stock.
 * Implementa la interfaz {@link StockRepository} y utiliza {@link JpaStockRepository} para acceder a la base de datos MySQL.
 */
@Component
public class StockRepositoryAdapter implements StockRepository{

    private final JpaStockRepository jpaStockRepository;
    private final StockEntityMapper stockEntityMapper;

    public StockRepositoryAdapter(JpaStockRepository jpaStockRepository, StockEntityMapper stockEntityMapper) {
        this.jpaStockRepository = jpaStockRepository;
        this.stockEntityMapper = stockEntityMapper;
    }

    /**
     * Busca el stock por el ID del producto.
     *
     * @param productId El ID del producto.
     * @return Un Optional que contiene el stock si se encuentra, o vacío si no.
     */
    @Override
    public java.util.Optional<com.microservice.inventory.domain.model.Stock> findByProductId(Long productId) {
        return jpaStockRepository.findByProductId(productId)
                .map(stockEntity -> stockEntityMapper.toDomain(stockEntity));
    }

    /**
     * Guarda el stock para un producto específico.
     *
     * @param productId El ID del producto.
     * @param stockModel El modelo de dominio del stock a guardar.
     * @return El stock guardado.
     */
    @Override
    public Stock save(Long productId, Stock stockModel) {
        StockEntity newStockEntity = StockEntity.builder()
                .productId(productId)
                .totalQuantity(stockModel.getTotalQuantity())
                .totalReserved(0)
                .build();

        newStockEntity = jpaStockRepository.save(newStockEntity);
                
        return stockEntityMapper.toDomain(newStockEntity);
    
    }
}
