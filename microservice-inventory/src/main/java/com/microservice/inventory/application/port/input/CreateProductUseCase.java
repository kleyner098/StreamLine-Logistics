package com.microservice.inventory.application.port.input;

public interface CreateProductUseCase {

    public Long createProduct(String sku, String name, String description, Double price, String stock);
}
