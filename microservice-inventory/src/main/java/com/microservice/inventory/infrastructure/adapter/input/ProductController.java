package com.microservice.inventory.infrastructure.adapter.input;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping()
    public String getProducts() {
        return "List of products";
    }

    @GetMapping("/{id}")
    public String getProductById(@RequestParam("id") String id) {
        return "Product details for ID: " + id;
    }

}
