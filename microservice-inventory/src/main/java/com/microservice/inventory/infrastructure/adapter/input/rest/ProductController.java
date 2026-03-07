package com.microservice.inventory.infrastructure.adapter.input.rest;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/inventory")
@Tag(name = "Product API", description = "API para la gestion de productos")
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

    /**
     * Endpoint para obtener la lista de productos con su stock
     * @return ResponseEntity con la lista de productos y su stock
     */
    @Operation(
        summary = "Obtener lista de productos", 
        description = "Obtiene una lista de todos los productos con su stock")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente")
    })
    @GetMapping()
    public ResponseEntity<List<ProductReponseDto>> getProducts() {
        
        List<ProductDetails> productDetailsList = getProductDetailsUseCase.getAllProducts();

        List<ProductReponseDto> responseList = productResponseDtoMapper.toResponseList(productDetailsList);

        return ResponseEntity.ok(responseList);
    }

    /**
     * Endpoint para obtener los detalles de un producto por su ID
     * @param id el ID del producto
     * @return ResponseEntity con los detalles del producto
     */
    @Operation(
        summary = "Obtener detalles de un producto", 
        description = "Obtiene los detalles de un producto por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalles del producto obtenidos exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductReponseDto> getProductById(@PathVariable Long id) {
    
        ProductDetails details = getProductDetailsUseCase.getProductDetails(id);

        if (details.product() == null && details.stock() == null) {
            return ResponseEntity.notFound().build();
        }

        ProductReponseDto response = productResponseDtoMapper.toResponse(details);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para crear un nuevo producto
     * @param createDto los datos para crear el producto
     * @return ResponseEntity con los detalles del producto creado
     */
    @Operation(
        summary = "Crear un nuevo producto", 
        description = "Crea un nuevo producto con los datos proporcionados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping()
    public ResponseEntity<ProductReponseDto> createProduct(@Valid @RequestBody ProductCreateDto createDto){

        Product product = productCreateDtoMapper.toDomain(createDto);
        Stock stock = stockCreateDtoMapper.toDomain(createDto);

        ProductDetails SavedProduct = createProductUseCase.createProduct(product, stock);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(SavedProduct.product().getId())
                .toUri();
        
        ProductReponseDto response = productResponseDtoMapper.toResponse(SavedProduct);

        return ResponseEntity.created(location).body(response);
    }
}
