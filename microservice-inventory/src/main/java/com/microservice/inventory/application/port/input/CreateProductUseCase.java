package com.microservice.inventory.application.port.input;

import com.microservice.inventory.domain.model.Product;
import com.microservice.inventory.domain.model.ProductDetails;
import com.microservice.inventory.domain.model.Stock;

/**
 * Interfaz para el caso de uso de creación de productos.
 * Define el método para crear un nuevo producto en el sistema, incluyendo su información y stock inicial.
 */
public interface CreateProductUseCase {

    /**
     * Crea un nuevo producto en el sistema con la información proporcionada y el stock inicial.
     * @param product
     * @param stock
     * @return  Los detalles del producto creado, incluyendo su ID asignado y la información de stock.
     */
    public ProductDetails createProduct(Product product, Stock stock);
}
