package com.microservice.inventory.application.dto;

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
    private Double price;
    private String stock;
}
