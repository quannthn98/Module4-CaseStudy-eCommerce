package com.casestudyecommerce.order.orders;

import com.casestudyecommerce.order.orderDetail.IOrderDetailService;
import com.casestudyecommerce.order.orderDetail.OrderDetail;
import com.casestudyecommerce.user.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@Service
public class OrderService implements IOrderService{
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Override
    public Page<Orders> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Orders save(Orders orders) {
        return orderRepository.save(orders);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Page<Orders> findByFullNameContaining(Pageable pageable, String fullName) {
        return orderRepository.findByFullNameContaining(pageable, fullName);
    }

    @Override
    public Page<Orders> findByUser(Pageable pageable, User user) {
        return orderRepository.findByUser(pageable, user);
    }

    @Override
    public HashMap<Orders, Iterable<OrderDetail>> findFullOderDetailByUser(User user) {
        HashMap<Orders, Iterable<OrderDetail>> ordersListHashMap = new HashMap<>();
        Iterable<Orders> orders = orderRepository.findByUser(user);
        for (Orders order: orders){
            Iterable<OrderDetail> orderDetails = orderDetailService.findAllByOrders(order);
            ordersListHashMap.put(order, orderDetails);
        }
        return ordersListHashMap;
    }
}
