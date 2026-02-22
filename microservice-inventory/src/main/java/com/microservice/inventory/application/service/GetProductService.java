package com.microservice.inventory.application.service;

import com.microservice.inventory.application.port.input.GetProductDetailsUseCase;
import com.microservice.inventory.application.port.output.ProductRepository;
import com.microservice.inventory.application.port.output.StockRepository;
import com.microservice.inventory.domain.exception.DomainException;
import com.microservice.inventory.domain.model.Product;
import com.microservice.inventory.domain.model.ProductDetails;
import com.microservice.inventory.domain.model.Stock;

public class GetProductService implements GetProductDetailsUseCase{

    private final ProductRepository productRepositoy;
    private final StockRepository stockRepository;

    public GetProductService(ProductRepository productRepositoy, StockRepository stockRepository) {
        this.productRepositoy = productRepositoy;
        this.stockRepository = stockRepository;
    }

    @Override
    public ProductDetails getProductDetails(Long productId) {
        Product product = productRepositoy.findById(productId)
            .orElseThrow(() -> new DomainException("Producto con ID " + productId + " no encontrado"));

        Stock stock = stockRepository.findByProductId(productId)
            .orElseThrow(() -> new DomainException("Stock no disponible para el producto con ID " + productId));
        
        return new ProductDetails(product, stock);
    }


}
