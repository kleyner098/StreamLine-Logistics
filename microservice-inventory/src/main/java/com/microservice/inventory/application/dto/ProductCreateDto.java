package com.microservice.inventory.application.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDto {

    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
}
