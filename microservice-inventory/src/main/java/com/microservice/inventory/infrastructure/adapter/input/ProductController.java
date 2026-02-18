package com.microservice.inventory.infrastructure.adapter.input;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.inventory.application.dto.ProductReponseDto;
import com.microservice.inventory.application.mapper.ProductDtoMapper;
import com.microservice.inventory.application.port.input.GetProductDetailsUseCase;
import com.microservice.inventory.domain.model.ProductDetails;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final GetProductDetailsUseCase getProductDetailsUseCase;
    private final ProductDtoMapper productDtoMapper;

    public ProductController(GetProductDetailsUseCase getProductDetailsUseCase, ProductDtoMapper productDtoMapper) {
        this.getProductDetailsUseCase = getProductDetailsUseCase;
        this.productDtoMapper = productDtoMapper;
    }

    @GetMapping()
    public String getProducts() {
        return "List of products";
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductReponseDto> getProductById(@PathVariable Long id) {
    
        ProductDetails details = getProductDetailsUseCase.getProductDetails(id);
    
        ProductReponseDto response = productDtoMapper.toResponse(details);
        
        return ResponseEntity.ok(response);
    }

}
