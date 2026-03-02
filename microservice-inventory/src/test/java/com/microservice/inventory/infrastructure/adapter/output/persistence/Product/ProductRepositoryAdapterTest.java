package com.microservice.inventory.infrastructure.adapter.output.persistence.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.microservice.inventory.domain.model.Product;
import com.microservice.inventory.domain.valueobject.Price;
import com.microservice.inventory.domain.valueobject.Sku;
import com.microservice.inventory.infrastructure.adapter.output.persistence.mappers.ProductEntityMapperImpl;
import com.microservice.inventory.infrastructure.shared.BaseRepositoryTest;


public class ProductRepositoryAdapterTest extends BaseRepositoryTest {

    @Autowired
    private JpaProductRepository jpaProductRepository;

    private ProductEntityMapperImpl productEntityMapper;

    private ProductRepositoryAdapter productRepositoryAdapter;
    private Long productId;

    @BeforeEach
    void setup() {
        productEntityMapper = new ProductEntityMapperImpl();
        productRepositoryAdapter = new ProductRepositoryAdapter(jpaProductRepository, productEntityMapper);

        jpaProductRepository.deleteAll(); // Limpiar la base de datos antes de cada prueba

        //Guardar un producto de prueba en la base de datos
        Product product = new Product(1L, new Sku("SKU123"), "Producto test", null, new Price(new BigDecimal("11.99")));
        productId = productRepositoryAdapter.save(product).getId();
    }

    @Test
    @DisplayName("Guardar un producto en la base de datos")
    void saveProductInDatabase(){

        //Given
        Product product = new Product(2L, new Sku("SKU456"), "Producto test", null, new Price(new BigDecimal("10.99")));

        //When
        Product savedProductEntity = productRepositoryAdapter.save(product);

        //then
        assertNotNull(savedProductEntity.getId());
        assertNotNull(jpaProductRepository.findById(savedProductEntity.getId()));
    }

    @Test
    @DisplayName("Buscar un producto por su id")
    void findProductById(){
        //When
        Product foundProduct = productRepositoryAdapter.findById(productId).orElse(null);

        //Then
        assertNotNull(foundProduct);
        assertEquals(foundProduct.getId(), productId);
    }

     @Test
    @DisplayName("Buscar un producto por su sku")
    void findProductBySku(){
        //When
        Product foundProduct = productRepositoryAdapter.findBySku("SKU123").orElse(null);

        //Then
        assertNotNull(foundProduct);
        assertEquals(foundProduct.getSku().value(), "SKU123");
    }

    @Test
    @DisplayName("Buscar todos los productos")
    void findAllProducts(){
        //Given
        List<Product> products = productRepositoryAdapter.findAll();

        //Then
        assertNotNull(products);
        assertEquals(1, products.size());
    }
}
