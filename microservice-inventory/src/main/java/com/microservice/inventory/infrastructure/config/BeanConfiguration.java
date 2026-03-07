package com.microservice.inventory.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microservice.inventory.application.port.input.GetProductDetailsUseCase;
import com.microservice.inventory.application.port.output.ProductRepository;
import com.microservice.inventory.application.port.output.StockRepository;
import com.microservice.inventory.application.service.CreateProductService;
import com.microservice.inventory.application.service.GetProductService;
import com.microservice.inventory.application.service.UpdateStockService;


/**
 * Configuración de beans para la inyección de dependencias.
 * <p>
 * Dado que la capa de dominio es agnóstica a Spring, este componente se encarga de
 * instanciar los servicios de aplicación (casos de uso) inyectando sus dependencias
 * de infraestructura (puertos de salida).
 */
@Configuration
public class BeanConfiguration {

    /**
     * Bean para el caso de uso de obtener detalles de un producto.
     * Se inyectan el adaptador de salida para productos {@link ProductRepository} y
     *  el adaptador de salida para stock {@link StockRepository}, ya que ambos son necesarios
     * @param productRepository Implementación de persistencia para productos  (MySQL/JPA).
     * @param stockRepository Implementación de persistencia para stock  (MySQL/JPA).
     * @return Instancia de {@link GetProductService} que implementa {@link GetProductDetailsUseCase}.
     */
    @Bean
    public GetProductDetailsUseCase getProductDetailsUseCase(ProductRepository productRepository,
        StockRepository stockRepository) {
        return new GetProductService(productRepository, stockRepository);
    }

    /**
     * Bean para el caso de uso de crear un producto.
     * Se inyectan el adaptador de salida para productos {@link ProductRepository} y
     *  el adaptador de salida para stock {@link StockRepository}, ya que ambos son necesarios
     * @param productRepository Implementación de persistencia para productos  (MySQL/JPA).
     * @param stockRepository Implementación de persistencia para stock  (MySQL/JPA).
     * @return Instancia de {@link CreateProductService} que implementa {@link CreateProductUseCase}.
     */
    @Bean
    public CreateProductService createProductService(ProductRepository productRepository, StockRepository stockRepository) {
        return new CreateProductService(productRepository, stockRepository);
    }

    /**
     * Bean para el caso de uso de actualizar el stock.
     * Se inyecta el adaptador de salida para stock {@link StockRepository}.
     * @param stockRepository Implementación de persistencia para stock  (MySQL/JPA).
     * @return Instancia de {@link UpdateStockService} que implementa {@link UpdateStockUseCase}.
     */
    @Bean
    public UpdateStockService updateStockService(StockRepository stockRepository) {
        return new UpdateStockService(stockRepository);
    }
}
