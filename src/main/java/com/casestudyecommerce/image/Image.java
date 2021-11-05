package com.casestudyecommerce.image;

import com.casestudyecommerce.product.Product;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @ManyToOne
    private Product product;

    public Image() {
    }

    public Image(Long id, String name, Product product) {
        this.id = id;
        this.name = name;
        this.product = product;
    }

}
