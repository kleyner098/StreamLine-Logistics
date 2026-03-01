package com.microservice.inventory.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microservice.inventory.application.exception.ApplicationException;
import com.microservice.inventory.application.port.output.ProductRepository;
import com.microservice.inventory.application.port.output.StockRepository;
import com.microservice.inventory.domain.model.Product;
import com.microservice.inventory.domain.model.ProductDetails;
import com.microservice.inventory.domain.model.Stock;
import com.microservice.inventory.domain.valueobject.Price;
import com.microservice.inventory.domain.valueobject.Sku;

@ExtendWith(MockitoExtension.class)
public class CreateProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private CreateProductService createProductService;

    @Test
    @DisplayName("Crear un nuevo producto correctamente")
    void createProductSuccessfully() {

        //Given
        Product product = new Product(1L, new Sku("SKU123"), "Producto test", null, new Price(new BigDecimal("10.99")));
        Stock stock = new Stock(1L, product.getId(), 100, 0);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productRepository.findBySku(anyString())).thenReturn(Optional.empty());
        when(stockRepository.save(any(Long.class), any(Stock.class))).thenReturn(stock);

        //When
        ProductDetails createdProduct = createProductService.createProduct(product, stock);

        //Then
        assertNotNull(createdProduct);
        assertEquals(createdProduct.product().getSku(), product.getSku());
        assertEquals(createdProduct.stock().getProductId(), stock.getProductId());
        //Verify
        verify(productRepository, times(1)).save(product);
        verify(stockRepository, times(1)).save(product.getId(), stock);
    }

    @Test
    @DisplayName("Intentar crear un producto con un SKU que ya existe")
    void createProductWithExistingSku() {
        //Given
        Product existingProduct = new Product(1L, new Sku("SKU123"), "Producto existente", null, new Price(new BigDecimal("10.99")));
        Stock stock = new Stock(1L, existingProduct.getId(), 100, 0);
        when(productRepository.findBySku(anyString())).thenReturn(Optional.of(existingProduct));

        // When & Then
        ApplicationException exception = assertThrows(ApplicationException.class, 
            () -> createProductService.createProduct(existingProduct, stock));

        assertEquals("El SKU ya existe", exception.getMessage());
            
        // Verify
        verify(productRepository, never()).save(any(Product.class));
    }

}
