package com.microservice.inventory.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * DTO para la cantidad de stock.
 * Contiene la cantidad de stock a agregar, reservar, liberar o cosumir para un producto específico.
 * Incluye validaciones para asegurar que la cantidad sea correcta antes de ser procesada por la capa de aplicación.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para la cantidad de stock a agregar, reservar, liberar o consumir para un producto específico")
public class StockQuantityDTO {

    @PositiveOrZero
    @Schema(description = "Cantidad de stock a agregar, reservar, liberar o consumir")
    private Integer amount;
}
