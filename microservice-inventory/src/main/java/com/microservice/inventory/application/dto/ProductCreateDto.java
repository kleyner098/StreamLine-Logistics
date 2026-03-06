package com.microservice.inventory.application.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "DTO para la creación de un producto")
public class ProductCreateDto {

    @Schema(description = "SKU del producto", example = "PROD-001", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String sku;

    @Schema(description = "Nombre del producto", example = "Camisa de prueba", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String name;

    @Schema(description = "Descripción del producto", example = "Camisa de prueba para testing")
    private String description;

    @Schema(description = "Precio del producto", example = "19.99", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal price;

    @Schema(description = "Cantidad de stock del producto. No puede ser negativa", example = "100", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @PositiveOrZero
    private Integer stock;
}
