package com.casestudyecommerce.order.orders;

import com.casestudyecommerce.IGeneralService;
import com.casestudyecommerce.order.orderDetail.OrderDetail;
import com.casestudyecommerce.user.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface IOrderService extends IGeneralService<Orders> {
    Page<Orders> findByFullNameContaining(Pageable pageable, String fullName);

    Page<Orders> findByUser(Pageable pageable, User user);

    HashMap<Orders, Iterable<OrderDetail>> findFullOderDetailByUser(User user);
}
