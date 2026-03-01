package com.microservice.inventory.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microservice.inventory.application.port.output.ProductRepository;
import com.microservice.inventory.application.port.output.StockRepository;
import com.microservice.inventory.domain.model.Product;
import com.microservice.inventory.domain.model.ProductDetails;
import com.microservice.inventory.domain.model.Stock;
import com.microservice.inventory.domain.valueobject.Price;
import com.microservice.inventory.domain.valueobject.Sku;

@ExtendWith(MockitoExtension.class)
public class GetProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private GetProductService getProductService;

    @Test
    @DisplayName("Buscar un producto por su ID")
    void testGetProductById() {
        //Given
        Product product = new Product(1L, new Sku("SKU123"), "Producto test", null, new Price(new BigDecimal("10.99")));
        Stock stock = new Stock(1L, product.getId(), 100, 0);
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(stockRepository.findByProductId(anyLong())).thenReturn(Optional.of(stock));

        Long productId = 1L;

        //When
        ProductDetails result = getProductService.getProductDetails(productId);

        //Then
        assertNotNull(result);
        assertNotNull(result.product());
        assertNotNull(result.stock());
        assertEquals(productId, result.product().getId());
        assertEquals(productId, result.stock().getProductId());

        //Verify
        verify(productRepository,times(1)).findById(productId);
        verify(stockRepository,times(1)).findByProductId(productId);
    }

    @Test
    @DisplayName("Buscar un producto por su ID - Producto no encontrado")
    void testGetProductById_ProductNotFound() {
        //Given
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        Long productId = 1L;

        //When
        ProductDetails result = getProductService.getProductDetails(productId);

        //Then
        assertNotNull(result);
        assertEquals(null, result.product());
        assertEquals(null, result.stock());
        //Verify
        verify(productRepository,times(1)).findById(productId);
        verify(stockRepository,times(1)).findByProductId(productId);
    }


    @Test
    @DisplayName("Buscar todos los productos")
    void testGetAllProducts() {
        //Given
        Product product = new Product(1L, new Sku("SKU123"), "Producto test", null, new Price(new BigDecimal("10.99")));
        Stock stock = new Stock(1L, product.getId(), 100, 0);
        Product product2 = new Product(2L, new Sku("SKU456"), "Producto test 2", null, new Price(new BigDecimal("20.99"))); 
        Stock stock2 = new Stock(2L, product2.getId(), 50, 0);
        when(productRepository.findAll()).thenReturn(List.of(product, product2));
        when(stockRepository.findByProductId(product.getId())).thenReturn(Optional.of(stock));
        when(stockRepository.findByProductId(product2.getId())).thenReturn(Optional.of(stock2));

        //When
        ArrayList<ProductDetails> result = getProductService.getAllProducts();

        //Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(product.getId(), result.get(0).product().getId());
        assertEquals(stock.getProductId(), result.get(0).stock().getProductId());
        assertEquals(product2.getId(), result.get(1).product().getId());
        assertEquals(stock2.getProductId(), result.get(1).stock().getProductId());

        //Verify
        verify(productRepository,times(1)).findAll();
        verify(stockRepository,times(1)).findByProductId(product.getId());
        verify(stockRepository,times(1)).findByProductId(product2.getId());
    }
}
