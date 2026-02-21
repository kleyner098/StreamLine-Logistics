package com.microservice.inventory.infrastructure.adapter.output.persistence.Product;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.microservice.inventory.application.port.output.ProductRepository;
import com.microservice.inventory.domain.model.Product;
import com.microservice.inventory.infrastructure.adapter.output.persistence.mappers.ProductEntityMapper;

@Component
public class ProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;
    private final ProductEntityMapper productEntityMapper;

    public ProductRepositoryAdapter(JpaProductRepository jpaProductRepository, ProductEntityMapper productEntityMapper) {
        this.jpaProductRepository = jpaProductRepository;
        this.productEntityMapper = productEntityMapper;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaProductRepository.findById(id)
                .map(productEntityMapper::toDomain);
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return jpaProductRepository.findBySku(sku)
                .map(productEntityMapper::toDomain);
    }



}
