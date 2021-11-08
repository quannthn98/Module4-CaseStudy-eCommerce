package com.casestudyecommerce.order.orderDetail;

import com.casestudyecommerce.IGeneralService;
import com.casestudyecommerce.order.orders.Orders;

public interface IOrderDetailService extends IGeneralService<OrderDetail> {
    Iterable<OrderDetail> findAllByOrders(Orders orders);
}
