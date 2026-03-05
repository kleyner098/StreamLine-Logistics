package com.microservice.inventory.application.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * DTO para la creación de un producto.
 * Contiene los datos necesarios para crear un nuevo producto en el sistema.
 * Incluye validaciones para asegurar que los datos sean correctos antes de ser procesados por la capa de aplicación.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDto {

    @NotBlank
    private String sku;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal price;

    @NotNull
    @PositiveOrZero
    private Integer stock;
}
