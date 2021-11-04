package com.casestudyecommerce.product;

import com.casestudyecommerce.brand.Brand;

import com.casestudyecommerce.image.Image;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotEmpty
    @Column(columnDefinition = "integer default 1")
    private int quantity;

    @Column(nullable = false)
    private double price;

    @Column(columnDefinition = "double default 0")
    private double saleOff;

    @NotEmpty
    @Column(nullable = false)
    private String mainImage;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Image> subImage;

    @ManyToOne
    private Brand brand;

    @NotEmpty
    @Column(nullable = false)
    private String description;

    public Product() {

    }

    public Product(Long id, String name, int quantity, double price, double saleOff, String mainImage, List<Image> subImage, Brand brand, String description) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.saleOff = saleOff;
        this.mainImage = mainImage;
        this.subImage = subImage;
        this.brand = brand;
        this.description = description;
    }
}
