package com.microservice.inventory.application.service;

import java.util.ArrayList;

import com.microservice.inventory.application.port.input.GetProductDetailsUseCase;
import com.microservice.inventory.application.port.output.ProductRepository;
import com.microservice.inventory.application.port.output.StockRepository;
import com.microservice.inventory.domain.model.Product;
import com.microservice.inventory.domain.model.ProductDetails;
import com.microservice.inventory.domain.model.Stock;


/**
 * Servicio para la obtención de detalles de productos.
 * <p>
 * Este servicio se encarga de recuperar la información detallada de un producto específico o de todos los productos disponibles en el sistema. 
 * Utiliza los repositorios de productos y stock para obtener la información necesaria y 
 * construir objetos ProductDetails que contienen tanto los datos del producto como su stock asociado.
 */
public class GetProductService implements GetProductDetailsUseCase{

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    public GetProductService(ProductRepository productRepository, StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }

    /**
     * Obtiene los detalles de un producto específico por su ID.
     * 
     * Utiliza el repositorio de productos {@link ProductRepository} para recuperar la información del producto,
     * y el repositorio de stock {@link StockRepository} para obtener la información del stock asociado al producto.
     *
     * @param productId El ID del producto del cual se desean obtener los detalles.
     * @return Un objeto ProductDetails que contiene la información del producto y su stock asociado, o null si el producto no existe.
     */
    @Override
    public ProductDetails getProductDetails(Long productId) {
        
        Product product = productRepository.findById(productId).orElse(null);

        Stock stock = stockRepository.findByProductId(productId).orElse(null);        
        return new ProductDetails(product, stock);
    }

    /**
     * Obtiene los detalles de todos los productos disponibles en el sistema.
     * 
     * Utiliza el repositorio de productos {@link ProductRepository} para recuperar la información de todos los productos,
     * y el repositorio de stock {@link StockRepository} para obtener la información del stock asociado a cada producto.
     *
     * @return Una lista de objetos ProductDetails que contienen la información de todos los productos y su stock asociado.
     */
    @Override
    public ArrayList<ProductDetails> getAllProducts() {
        ArrayList<ProductDetails> allProducts = new ArrayList<>();

        for (Product product : productRepository.findAll()) {
            Stock stock = stockRepository.findByProductId(product.getId()).orElse(null);
            
            allProducts.add(new ProductDetails(product, stock));
        }

        return allProducts;
    }


}
