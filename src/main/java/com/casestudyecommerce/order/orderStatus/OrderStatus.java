package com.casestudyecommerce.order.orderStatus;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    public OrderStatus() {
    }

    public OrderStatus(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
