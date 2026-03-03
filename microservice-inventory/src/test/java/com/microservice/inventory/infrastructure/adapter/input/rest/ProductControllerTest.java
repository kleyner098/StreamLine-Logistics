package com.microservice.inventory.infrastructure.adapter.input.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.inventory.application.dto.ProductCreateDto;
import com.microservice.inventory.application.mapper.ProductResponseDtoMapperImpl;
import com.microservice.inventory.application.port.input.CreateProductUseCase;
import com.microservice.inventory.application.port.input.GetProductDetailsUseCase;
import com.microservice.inventory.domain.model.Product;
import com.microservice.inventory.domain.model.ProductDetails;
import com.microservice.inventory.domain.model.Stock;
import com.microservice.inventory.domain.valueobject.Price;
import com.microservice.inventory.domain.valueobject.Sku;
import com.microservice.inventory.infrastructure.adapter.input.rest.mapper.ProductCreateDtoMapper;
import com.microservice.inventory.infrastructure.adapter.input.rest.mapper.ProductCreateDtoMapperImpl;
import com.microservice.inventory.infrastructure.adapter.input.rest.mapper.StockCreateDtoMapper;

@WebMvcTest(controllers = ProductController.class)
@Import({ProductCreateDtoMapperImpl.class, ProductResponseDtoMapperImpl.class, StockCreateDtoMapper.class})
public class ProductControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CreateProductUseCase createProductUseCase;

    @MockitoBean
    private GetProductDetailsUseCase getProductDetailsUseCase;

    @MockitoSpyBean
    ProductCreateDtoMapper productCreateDtoMapper;

    @MockitoBean
    StockCreateDtoMapper stockCreateDtoMapper;

    @MockitoSpyBean
    ProductResponseDtoMapperImpl productResponseDtoMapper;

    Long productId;
    ProductDetails productDetails;

    @BeforeEach
    public void setup() {
        productId = 1L;
        Product product = new Product(productId,new Sku("SKU123"), "Producto 1", "Descripción del producto 1", new Price(new BigDecimal("9.99")));
        Stock stock = new Stock(1L, productId, 100, 0);
        productDetails = new ProductDetails(product, stock);
    }


    @Test
    @DisplayName("Obtener un producto por su ID")
    public void testGetProductById() throws Exception {
        // Given
        
        BDDMockito.given(getProductDetailsUseCase.getProductDetails(productId)).willReturn(productDetails);

        // When & Then
        mockMvc.perform(get("/api/v1/products/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Producto 1"))
                .andExpect(jsonPath("$.description").value("Descripción del producto 1"))
                .andExpect(jsonPath("$.price").value(9.99))
                .andExpect(jsonPath("$.availableStock").value(100));
    }

    @Test
    @DisplayName("Obtener un producto por ID no encontrado")
    public void testGetProductByIdNotFound() throws Exception {
        // Given
        BDDMockito.given(getProductDetailsUseCase.getProductDetails(productId)).willReturn(new ProductDetails(null, null));

        // When & Then
        mockMvc.perform(get("/api/v1/products/{id}", productId))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Obtener todos los productos")
    public void testGetAllProducts() throws Exception {
        
        ArrayList<ProductDetails> productDetailsList = new ArrayList<>();
        productDetailsList.add(productDetails);

        BDDMockito.given(getProductDetailsUseCase.getAllProducts()).willReturn(productDetailsList);

        // When & Then
        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Producto 1"))
                .andExpect(jsonPath("$[0].description").value("Descripción del producto 1"))
                .andExpect(jsonPath("$[0].price").value(9.99))
                .andExpect(jsonPath("$[0].availableStock").value(100));
    }

    @Test
    @DisplayName("Crear un nuevo producto")
    public void testCreateProduct() throws Exception {
        // Given

        Product product = new Product(2L,new Sku("SKU456"), "Producto 1", "Descripción del producto 1", new Price(new BigDecimal("9.99")));
        Stock stock = new Stock(1L, 2L, 100, 0);

        ProductCreateDto createDto = new ProductCreateDto();
        createDto.setSku("SKU456");
        createDto.setName("Producto 1");
        createDto.setDescription("Descripción del producto 1");
        createDto.setPrice(new BigDecimal("9.99"));
        createDto.setStock(100);

        BDDMockito.given(createProductUseCase.createProduct(any(), any())).willReturn(new ProductDetails(product, stock));

        // When & Then
        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(createDto.getName()))
                .andExpect(jsonPath("$.description").value(createDto.getDescription()))
                .andExpect(jsonPath("$.price").value(createDto.getPrice()))
                .andExpect(jsonPath("$.availableStock").value(createDto.getStock()));
    }
   
}
