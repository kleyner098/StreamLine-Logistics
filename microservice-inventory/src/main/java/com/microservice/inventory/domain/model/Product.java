package com.microservice.inventory.domain.model;

import com.microservice.inventory.domain.exception.DomainException;


/**
 * Representa un producto en el sistema de inventario.
 * Un producto tiene un SKU único, un nombre, una descripción y un precio.
 * El SKU y el nombre no pueden ser nulos, vacíos o en blanco.
 * El precio debe ser un valor positivo con hasta 2 decimales.
 */
public class Product {

    private final Long id;
    private final Sku sku;
    private final String name;
    private final String description;
    private final Price price;

    Product( Long id, Sku sku, String name, String description, Price price) {
        validateName(name);
        
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // --- Métodos de cambio de estado (Inmutabilidad) ---
    public Product updateSku(Sku newSku) {
        return new Product(this.id, newSku, this.name, this.description, this.price);
    }

    public Product updateName(String newName) {
        validateName(newName);
        return new Product(this.id, this.sku, newName, this.description, this.price);
    }

    public Product withDescription(String newDescription) {
        return new Product(this.id, this.sku, this.name, newDescription, this.price);
    }

    public Product updatePrice(Price newPrice) {
        return new Product(this.id, this.sku, this.name, this.description, newPrice);
    }

    // Validaciones 
    private void validateName(String name) {
        if (name == null || name.isBlank()) throw new DomainException("Nombre inválido");
    }

    // Getters
    public Long getId() {return id;}
    public Sku getSku() {return sku;}
    public String getName() {return name;}
    public String getDescription() {return description;}
    public Price getPrice() {return price;}
}
