package com.microservice.inventory.application.port.input;

import java.util.ArrayList;

import com.microservice.inventory.domain.model.ProductDetails;

/**
 * Interfaz para el caso de uso de obtención de detalles de productos.
 * Define los métodos para recuperar información sobre productos en el sistema.
 */
public interface GetProductDetailsUseCase {

    /**
     * Recupera los detalles de un producto específico utilizando su ID.
     * @param productId
     * @return Los detalles del producto, incluyendo su información y stock actual. Si el producto no existe, se puede retornar null o lanzar una excepción según la implementación.
     */
    public ProductDetails getProductDetails(Long productId);

    /**
     * Recupera los detalles de todos los productos en el sistema.
     * @return Una lista con los detalles de todos los productos.
     */
    public ArrayList<ProductDetails> getAllProducts();
}
