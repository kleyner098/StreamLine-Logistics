package com.microservice.inventory.infrastructure.adapter.output.persistence.Stock;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Entidad JPA para representar el stock de un producto en la base de datos.
 * Esta clase se utiliza para mapear los datos del stock a la tabla "stocks" en la base de datos.
 * No debe contener lógica de negocio, solo atributos y anotaciones para la persistencia.
 */
@Entity
@Table(name = "stocks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false, unique = true)
    private Long productId;

    @Column(nullable = false)
    private int totalQuantity;

    @Column(nullable = false)
    private int totalReserved;

}
