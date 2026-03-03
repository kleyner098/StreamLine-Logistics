package com.microservice.inventory.infrastructure.adapter.output.persistence.stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.microservice.inventory.domain.model.Stock;
import com.microservice.inventory.infrastructure.adapter.output.persistence.BaseRepositoryTest;
import com.microservice.inventory.infrastructure.adapter.output.persistence.Stock.JpaStockRepository;
import com.microservice.inventory.infrastructure.adapter.output.persistence.Stock.StockRepositoryAdapter;
import com.microservice.inventory.infrastructure.adapter.output.persistence.mappers.StockEntityMapperImpl;

public class StockRepositoryAdapterTest extends BaseRepositoryTest {

    @Autowired
    private JpaStockRepository jpaStockRepository;

    private StockEntityMapperImpl stockEntityMapper;

    private StockRepositoryAdapter stockRepositoryAdapter;

    private Long productId;

    @BeforeEach
    void setup() {
        stockEntityMapper = new StockEntityMapperImpl();
        stockRepositoryAdapter = new StockRepositoryAdapter(jpaStockRepository, stockEntityMapper);

        
        // Limpiar la base de datos antes de cada prueba
        jpaStockRepository.deleteAll(); 

        productId = 1L;

        //Guardar un stock de prueba en la base de datos
        Stock stock = new Stock(2L, productId, 100, 0);
        stockRepositoryAdapter.save(productId, stock);
    }


    @Test
    @DisplayName("Guardar un stock en la base de datos")
    void saveStockInDatabase(){

        //Given
        Stock newstock = new Stock(2L,2L, 50, 0);

        //When
        Stock savedStockEntity = stockRepositoryAdapter.save(2L, newstock);

        //then
        assertNotNull(savedStockEntity.getId());
        assertNotNull(jpaStockRepository.findById(savedStockEntity.getId()));
    }

    @Test
    @DisplayName("Buscar un stock por su id de producto")
    void findStockByProductId(){
        //When
        Stock foundStock = stockRepositoryAdapter.findByProductId(productId).orElse(null);

        //Then
        assertNotNull(foundStock);
        assertEquals(foundStock.getProductId(), productId);
    }
}
