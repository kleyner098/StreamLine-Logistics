package com.microservice.inventory.domain.Service;

import com.microservice.inventory.application.port.input.CreateProductUseCase;
import com.microservice.inventory.application.port.output.ProductRepository;
import com.microservice.inventory.application.port.output.StockRepository;
import com.microservice.inventory.domain.exception.DomainException;

public class CreateProductDomainService implements CreateProductUseCase{

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    public CreateProductDomainService(ProductRepository productRepository, StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }


    @Override
    public Long createProduct(String sku, String name, String description, Double price, String stock) {
        
        if (productRepository.findBySku(sku).isPresent()) {
            throw new DomainException("El SKU ya existe");
        }

        Long productId = productRepository.save(sku, name, description, price);
        stockRepository.save(productId, stock);

        return productId;
    }

}
