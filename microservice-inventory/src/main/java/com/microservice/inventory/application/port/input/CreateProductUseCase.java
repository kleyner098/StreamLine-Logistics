package com.microservice.inventory.application.port.input;

import com.microservice.inventory.domain.model.Product;
import com.microservice.inventory.domain.model.Stock;

public interface CreateProductUseCase {

    public Long createProduct(Product product, Stock stock);
}
