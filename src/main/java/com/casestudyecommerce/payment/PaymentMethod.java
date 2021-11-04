package com.casestudyecommerce.payment;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    public PaymentMethod() {
    }

    public PaymentMethod(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
