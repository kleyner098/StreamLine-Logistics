package com.microservice.order.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para la creación de una orden")
public class OrderCreateDto {

    @NotNull
    @NotEmpty
    private String customerId;

    @NotNull
    @NotEmpty
    private String skuItem;
    
    @NotNull
    private int quantity;

}
