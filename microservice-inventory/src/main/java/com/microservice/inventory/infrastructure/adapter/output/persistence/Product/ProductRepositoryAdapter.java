package com.microservice.inventory.infrastructure.adapter.output.persistence.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.microservice.inventory.application.port.output.ProductRepository;
import com.microservice.inventory.domain.model.Product;
import com.microservice.inventory.infrastructure.adapter.output.persistence.mappers.ProductEntityMapper;


/**
 * Adaptador para la implementación del repositorio de productos.
 * Este adaptador utiliza JPA {@link JpaProductRepository} para interactuar con la base de datos MySQL y 
 * MapStruct {@link ProductEntityMapper} para mapear entre entidades y modelos de dominio.
 */
@Component
public class ProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;
    private final ProductEntityMapper productEntityMapper;

    public ProductRepositoryAdapter(JpaProductRepository jpaProductRepository, ProductEntityMapper productEntityMapper) {
        this.jpaProductRepository = jpaProductRepository;
        this.productEntityMapper = productEntityMapper;
    }

    /**
     * Busca un producto por su ID.
     *
     * @param id El ID del producto a buscar.
     * @return Un Optional que contiene el producto si se encuentra, o vacío si no.
     */
    @Override
    public Optional<Product> findById(Long id) {
        return jpaProductRepository.findById(id)
                .map(productEntityMapper::toDomain);
    }

    /**
     * Busca un producto por su SKU.
     *
     * @param sku El SKU del producto a buscar.
     * @return Un Optional que contiene el producto si se encuentra, o vacío si no.
     */
    @Override
    public Optional<Product> findBySku(String sku) {
        return jpaProductRepository.findBySku(sku)
                .map(productEntityMapper::toDomain);
    }

    /**
     * Guarda un producto en la base de datos.
     *
     * @param productModel El modelo de dominio del producto a guardar.
     * @return El producto guardado.
     */
    @Override
    public Product save(Product productModel) {
        ProductEntity newProductEntity = ProductEntity.builder()
                .sku(productModel.getSku().value())
                .name(productModel.getName())
                .description(productModel.getDescription())
                .price(productModel.getPrice().amount())
                .build();
               
        ProductEntity savedEntity = jpaProductRepository.save(newProductEntity);
        return productEntityMapper.toDomain(savedEntity);    
    }

    /**
     * Encuentra todos los productos.
     *
     * @return Una lista de todos los productos.
     */
    @Override
    public List<Product> findAll() {
        List<ProductEntity> productEntities = jpaProductRepository.findAll();
        return productEntityMapper.toDomainList(productEntities);
    }



}
