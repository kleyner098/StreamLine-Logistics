package com.microservice.inventory.application.port.output;

import java.util.List;
import java.util.Optional;

import com.microservice.inventory.domain.model.Product;

/**
 * Repositorio para el acceso a los datos de productos.
 * Define los métodos para interactuar con la capa de persistencia, como buscar productos por ID o SKU, 
 * guardar nuevos productos, etc.
 */
public interface ProductRepository {

    /**
     * Busca todos los productos disponibles en el sistema.
     * @return Una lista de todos los productos.
     */
    List<Product> findAll();

    /**
     * Busca un producto por su ID.
     * @param id
     * @return Un Optional que contiene el producto si se encuentra, o vacío si no existe.
     */
    Optional<Product> findById(Long id);

    /**
     * Busca un producto por su SKU.
     * @param sku
     * @return Un Optional que contiene el producto si se encuentra, o vacío si no existe.
     */
    Optional<Product> findBySku(String sku);

    /**
     * Guarda un nuevo producto en el sistema.
     * @param product
     * @return El producto guardado.
     */
    Product save(Product product);
    
}
