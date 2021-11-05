package com.casestudyecommerce.restController.order;

import com.casestudyecommerce.cart.CartDetail;
import com.casestudyecommerce.cart.ICartService;
import com.casestudyecommerce.order.orderDetail.IOrderDetailService;
import com.casestudyecommerce.order.orderDetail.OrderDetail;
import com.casestudyecommerce.order.orders.IOrderService;
import com.casestudyecommerce.order.orders.Orders;
import com.casestudyecommerce.product.Product;
import com.casestudyecommerce.security.model.UserPrinciple;
import com.casestudyecommerce.user.users.IUserService;
import com.casestudyecommerce.user.users.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderRestController {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private ICartService cartService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<Page<Orders>> findAll(@RequestParam(name = "q") Optional<String> q, Pageable pageable) {
        Page<Orders> orders;
        if (q.isPresent()) {
            orders = orderService.findByFullNameContaining(pageable, q.get());
        } else {
            orders = orderService.findAll(pageable);
        }
        if (orders.getTotalElements() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<HashMap<Orders, List<OrderDetail>>> newOrder(@RequestBody Orders orders, Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        User user = userService.findByUsername(userPrinciple.getUsername());
        Orders savedOrder = orderService.save(orders);
        Iterable<CartDetail> cartDetails = cartService.findByUser(user);
        List<OrderDetail> orderDetails = convertCartDetailToOrderDetail(cartDetails, savedOrder);
        HashMap<Orders, List<OrderDetail>> ordersListHashMap = new HashMap<>();
        ordersListHashMap.put(orders, orderDetails);
        return new ResponseEntity<>(ordersListHashMap, HttpStatus.OK);
    }

    private List<OrderDetail> convertCartDetailToOrderDetail(Iterable<CartDetail> cartDetails, Orders orders) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (CartDetail cartDetail : cartDetails) {

            Product product = cartDetail.getProduct();
            int quantity = cartDetail.getQuantity();
            double price = product.getPrice();
            double saleOff = product.getSaleOff();
            OrderDetail orderDetail = new OrderDetail(product, orders, price, saleOff, quantity);
            orderDetailService.save(orderDetail);
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }

}
