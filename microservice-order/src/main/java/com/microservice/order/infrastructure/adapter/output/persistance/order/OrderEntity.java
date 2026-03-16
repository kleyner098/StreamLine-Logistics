package com.microservice.order.infrastructure.adapter.output.persistance.order;

import java.math.BigDecimal;
import java.util.List;

import com.microservice.order.infrastructure.adapter.output.persistance.orderItem.OrderItemEntity;
import com.microservice.order.domain.model.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number", nullable = false, unique = true)
    private String orderNumber;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

    /* 
        Relación con OrderItemEntity. 
        La anotación @OneToMany indica que una orden puede tener múltiples items asociados.
        El atributo mappedBy indica que la relación es bidireccional y que la entidad OrderItemEntity es la propietaria de la relación.
        CascadeType.ALL se utiliza para propagar todas las operaciones (persistir, eliminar, etc.) a los items asociados.
        orphanRemoval = true asegura que si un item se elimina de la lista de items de una orden, también se eliminará de la base de datos.
    */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> items;

}
