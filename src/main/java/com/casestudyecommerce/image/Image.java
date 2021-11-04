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

    public Image() {
    }

    public Image(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
