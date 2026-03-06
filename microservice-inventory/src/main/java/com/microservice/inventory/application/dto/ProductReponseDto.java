package com.microservice.inventory.application.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * DTO para la respuesta de un producto.
 * Contiene los datos necesarios para devolver información sobre un producto en el sistema.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para la respuesta de un producto")
public class ProductReponseDto {

    @Schema(description = "SKU del producto", example = "PROD-001")
    private String sku;

    @Schema(description = "Nombre del producto", example = "Camisa de prueba")
    private String name;

    @Schema(description = "Descripción del producto", example = "Camisa de prueba para testing")
    private String description;

    @Schema(description = "Precio del producto", example = "19.99")
    private BigDecimal price;

    @Schema(description = "Cantidad de stock disponible del producto", example = "100")
    private Integer availableStock;


}
