package com.microservice.inventory.infrastructure.adapter.input.rest;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.inventory.application.dto.ProductCreateDto;
import com.microservice.inventory.application.dto.ProductReponseDto;
import com.microservice.inventory.application.mapper.ProductResponseDtoMapper;
import com.microservice.inventory.application.port.input.CreateProductUseCase;
import com.microservice.inventory.application.port.input.GetProductDetailsUseCase;
import com.microservice.inventory.domain.model.Product;
import com.microservice.inventory.domain.model.ProductDetails;
import com.microservice.inventory.domain.model.Stock;
import com.microservice.inventory.infrastructure.adapter.input.rest.mapper.ProductCreateDtoMapper;
import com.microservice.inventory.infrastructure.adapter.input.rest.mapper.StockCreateDtoMapper;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final GetProductDetailsUseCase getProductDetailsUseCase;
    private final ProductResponseDtoMapper productResponseDtoMapper;
    private final CreateProductUseCase createProductUseCase;
    private final StockCreateDtoMapper stockCreateDtoMapper;
    private final ProductCreateDtoMapper productCreateDtoMapper;

    public ProductController(GetProductDetailsUseCase getProductDetailsUseCase, 
            ProductResponseDtoMapper productResponseDtoMapper, 
            CreateProductUseCase createProductUseCase, 
            StockCreateDtoMapper stockCreateDtoMapper, 
            ProductCreateDtoMapper productCreateDtoMapper) {
        this.getProductDetailsUseCase = getProductDetailsUseCase;
        this.productResponseDtoMapper = productResponseDtoMapper;
        this.createProductUseCase = createProductUseCase;
        this.stockCreateDtoMapper = stockCreateDtoMapper;
        this.productCreateDtoMapper = productCreateDtoMapper;
    }

    @GetMapping()
    public ResponseEntity<List<ProductReponseDto>> getProducts() {
        
        List<ProductDetails> productDetailsList = getProductDetailsUseCase.getAllProducts();

        List<ProductReponseDto> responseList = productResponseDtoMapper.toResponseList(productDetailsList);

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductReponseDto> getProductById(@PathVariable Long id) {
    
        ProductDetails details = getProductDetailsUseCase.getProductDetails(id);
    
        ProductReponseDto response = productResponseDtoMapper.toResponse(details);
        
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<ProductReponseDto> createProduct(@Valid @RequestBody ProductCreateDto createDto){


        
        Product product = productCreateDtoMapper.toDomain(createDto);
        Stock stock = stockCreateDtoMapper.toDomain(createDto);

        ProductDetails newProduct = createProductUseCase.createProduct(product, stock);
        
        
        ProductReponseDto response = productResponseDtoMapper.toResponse(newProduct);
        return ResponseEntity.ok(response);
    }
}
