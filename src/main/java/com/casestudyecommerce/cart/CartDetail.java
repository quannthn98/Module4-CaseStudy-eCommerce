package com.casestudyecommerce.cart;

import com.casestudyecommerce.product.Product;
import com.casestudyecommerce.user.users.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private int quantity;

    public CartDetail() {
    }

    public CartDetail(Long id, User user, Product product, int quantity) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }
}
