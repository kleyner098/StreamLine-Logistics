package com.microservice.inventory.application.port.input;

import com.microservice.inventory.domain.model.Product;
import com.microservice.inventory.domain.model.ProductDetails;
import com.microservice.inventory.domain.model.Stock;

public interface CreateProductUseCase {

    public ProductDetails createProduct(Product product, Stock stock);
}
