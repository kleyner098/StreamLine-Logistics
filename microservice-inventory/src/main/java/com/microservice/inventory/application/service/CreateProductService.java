package com.microservice.inventory.application.service;

import com.microservice.inventory.application.exception.ApplicationException;
import com.microservice.inventory.application.port.input.CreateProductUseCase;
import com.microservice.inventory.application.port.output.ProductRepository;
import com.microservice.inventory.application.port.output.StockRepository;
import com.microservice.inventory.domain.model.Product;
import com.microservice.inventory.domain.model.ProductDetails;
import com.microservice.inventory.domain.model.Stock;

public class CreateProductService implements CreateProductUseCase{

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    public CreateProductService(ProductRepository productRepository, StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }


    @Override
    public ProductDetails createProduct(Product productModel, Stock stockModel) {
        
        if (productRepository.findBySku(productModel.getSku().value()).isPresent()) {
            throw new ApplicationException("El SKU ya existe");
        }

        Product product = productRepository.save(productModel);
        Stock stock = stockRepository.save(product.getId(), stockModel);

        return new ProductDetails(product, stock);
    }

}
