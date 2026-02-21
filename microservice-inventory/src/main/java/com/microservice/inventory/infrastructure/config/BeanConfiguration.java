package com.microservice.inventory.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microservice.inventory.application.port.input.GetProductDetailsUseCase;
import com.microservice.inventory.application.port.output.ProductRepository;
import com.microservice.inventory.application.port.output.StockRepository;
import com.microservice.inventory.domain.Service.CreateProductDomainService;
import com.microservice.inventory.domain.Service.GetProductDomainService;

@Configuration
public class BeanConfiguration {

    @Bean
    public GetProductDetailsUseCase getProductDetailsUseCase(ProductRepository productRepository,
        StockRepository stockRepository) {
        
        
        return new GetProductDomainService(productRepository, stockRepository);
    }

    @Bean
    public CreateProductDomainService createProductDomainService(ProductRepository productRepository, StockRepository stockRepository) {
        return new CreateProductDomainService(productRepository, stockRepository);
    }
}
