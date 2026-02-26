package com.microservice.inventory.application.service;

import java.util.ArrayList;

import com.microservice.inventory.application.exception.ApplicationException;
import com.microservice.inventory.application.port.input.GetProductDetailsUseCase;
import com.microservice.inventory.application.port.output.ProductRepository;
import com.microservice.inventory.application.port.output.StockRepository;
import com.microservice.inventory.domain.model.Product;
import com.microservice.inventory.domain.model.ProductDetails;
import com.microservice.inventory.domain.model.Stock;

public class GetProductService implements GetProductDetailsUseCase{

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    public GetProductService(ProductRepository productRepository, StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public ProductDetails getProductDetails(Long productId) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ApplicationException("Producto con ID " + productId + " no encontrado"));

        Stock stock = stockRepository.findByProductId(productId)
            .orElseThrow(() -> new ApplicationException("Stock no disponible para el producto con ID " + productId));
        
        return new ProductDetails(product, stock);
    }

    @Override
    public ArrayList<ProductDetails> getAllProducts() {
        ArrayList<ProductDetails> allProducts = new ArrayList<>();

        for (Product product : productRepository.findAll()) {
            Stock stock = stockRepository.findByProductId(product.getId())
                .orElseThrow(() -> new ApplicationException("Stock no disponible para el producto con ID " + product.getId()));
            
            allProducts.add(new ProductDetails(product, stock));
        }

        return allProducts;
    }


}
