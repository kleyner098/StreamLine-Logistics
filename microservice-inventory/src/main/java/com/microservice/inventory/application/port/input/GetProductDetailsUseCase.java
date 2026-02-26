package com.microservice.inventory.application.port.input;

import java.util.ArrayList;

import com.microservice.inventory.domain.model.ProductDetails;

public interface GetProductDetailsUseCase {

    public ProductDetails getProductDetails(Long productId);
    public ArrayList<ProductDetails> getAllProducts();
}
