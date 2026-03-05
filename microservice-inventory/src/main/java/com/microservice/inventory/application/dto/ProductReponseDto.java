package com.microservice.inventory.application.dto;

import java.math.BigDecimal;


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
public class ProductReponseDto {

    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer availableStock;


}
