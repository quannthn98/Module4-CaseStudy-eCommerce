package com.casestudyecommerce.deliverFirm;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class DeliverFirm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name Not Null")
    @Column(columnDefinition = "VARCHAR(50)",unique = true, nullable = false)
    private String name;

    public DeliverFirm() {
    }

    public DeliverFirm(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
