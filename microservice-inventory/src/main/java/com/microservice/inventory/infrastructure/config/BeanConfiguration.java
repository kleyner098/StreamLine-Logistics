package com.microservice.inventory.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microservice.inventory.application.port.input.GetProductDetailsUseCase;
import com.microservice.inventory.application.port.output.ProductRepository;
import com.microservice.inventory.application.port.output.StockRepository;
import com.microservice.inventory.application.service.CreateProductService;
import com.microservice.inventory.application.service.GetProductService;

@Configuration
public class BeanConfiguration {

    @Bean
    public GetProductDetailsUseCase getProductDetailsUseCase(ProductRepository productRepository,
        StockRepository stockRepository) {
        
        
        return new GetProductService(productRepository, stockRepository);
    }

    @Bean
    public CreateProductService createProductService(ProductRepository productRepository, StockRepository stockRepository) {
        return new CreateProductService(productRepository, stockRepository);
    }
}
