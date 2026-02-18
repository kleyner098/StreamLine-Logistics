package com.microservice.inventory.application.port.input;

import com.microservice.inventory.domain.model.ProductDetails;

public interface GetProductDetailsUseCase {

    public ProductDetails getProductDetails(Long productId);
}
