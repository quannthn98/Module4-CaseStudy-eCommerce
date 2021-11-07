package com.casestudyecommerce.order.orders;

import com.casestudyecommerce.deliverFirm.DeliverFirm;
import com.casestudyecommerce.payment.PaymentMethod;
import com.casestudyecommerce.user.users.User;
import com.casestudyecommerce.order.orderStatus.OrderStatus;
import com.casestudyecommerce.order.orderDetail.OrderDetail;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String dateCreated;

    @ManyToOne
    private DeliverFirm deliverFirm;

    @ManyToOne
    private PaymentMethod paymentMethod;

    @ManyToOne
    private OrderStatus orderStatus;

    @NotEmpty
    private String fullName;

    @NotEmpty
    private String address;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String email;

    private String note;

    public Orders() {
    }

    public Orders(Long id, User user, DeliverFirm deliverFirm, PaymentMethod paymentMethod, OrderStatus orderStatus, String fullName, String address, String phone, String email, String note) {
        this.id = id;
        this.user = user;
        this.deliverFirm = deliverFirm;
        this.paymentMethod = paymentMethod;
        this.orderStatus = orderStatus;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.note = note;
    }
}
