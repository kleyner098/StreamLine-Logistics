package com.microservice.inventory.domain.Service;

import com.microservice.inventory.application.port.input.CreateProductUseCase;
import com.microservice.inventory.application.port.output.ProductRepository;
import com.microservice.inventory.application.port.output.StockRepository;
import com.microservice.inventory.domain.exception.DomainException;
import com.microservice.inventory.domain.model.Product;
import com.microservice.inventory.domain.model.Stock;

public class CreateProductDomainService implements CreateProductUseCase{

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    public CreateProductDomainService(ProductRepository productRepository, StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }


    @Override
    public Long createProduct(Product product, Stock stock) {
        
        if (productRepository.findBySku(product.getSku().value()).isPresent()) {
            throw new DomainException("El SKU ya existe");
        }

        Long productId = productRepository.save(product);
        stockRepository.save(productId, stock.getTotalQuantity());

        return productId;
    }

}
