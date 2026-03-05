package com.microservice.inventory.application.service;

import com.microservice.inventory.application.exception.ApplicationException;
import com.microservice.inventory.application.port.input.CreateProductUseCase;
import com.microservice.inventory.application.port.output.ProductRepository;
import com.microservice.inventory.application.port.output.StockRepository;
import com.microservice.inventory.domain.model.Product;
import com.microservice.inventory.domain.model.ProductDetails;
import com.microservice.inventory.domain.model.Stock;


/**
 * Servicio para la creación de productos.
 * <p>
 * Este servicio se encarga de validar y procesar la creación de un nuevo producto en el sistema. 
 * Verifica que el SKU del producto no exista previamente y, si es válido, guarda el producto 
 * y su stock asociado en los repositorios correspondientes.
 */
public class CreateProductService implements CreateProductUseCase{

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    public CreateProductService(ProductRepository productRepository, StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }

    /**
     * Crea un nuevo producto en el sistema.
     * 
     * Utiliza el repositorio de productos {@link ProductRepository} para verificar la unicidad del SKU y guardar el producto,
     * y el repositorio de stock {@link StockRepository} para guardar la información del stock asociado al producto.
     *
     * @param productModel El modelo del producto a crear.
     * @param stockModel El modelo del stock asociado al producto.
     * @return Un objeto ProductDetails que contiene la información del producto creado y su stock.
     * @throws ApplicationException Si el SKU del producto ya existe en el sistema.
     */
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
