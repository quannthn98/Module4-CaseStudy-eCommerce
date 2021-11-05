package com.casestudyecommerce.order.orderDetail;

import com.casestudyecommerce.order.orders.Orders;
import com.casestudyecommerce.product.Product;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Orders orders;

    @NotEmpty
    @Column(nullable = false)
    private double price;

    @Column(columnDefinition = "double default 0")
    private double sellOff;

    @Column(columnDefinition = "integer default 1")
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(Long id, Product product, double price, double sellOff, int quantity) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.sellOff = sellOff;
        this.quantity = quantity;
    }

    public OrderDetail(Product product, Orders orders, double price, double sellOff, int quantity) {
        this.product = product;
        this.orders = orders;
        this.price = price;
        this.sellOff = sellOff;
        this.quantity = quantity;
    }
}
