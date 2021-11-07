package com.casestudyecommerce.restController.order;

import com.casestudyecommerce.cart.CartDetail;
import com.casestudyecommerce.cart.ICartService;
import com.casestudyecommerce.order.orderDetail.IOrderDetailService;
import com.casestudyecommerce.order.orderDetail.OrderDetail;
import com.casestudyecommerce.order.orderStatus.IOrderStatusService;
import com.casestudyecommerce.order.orderStatus.OrderStatus;
import com.casestudyecommerce.order.orders.IOrderService;
import com.casestudyecommerce.order.orders.Orders;
import com.casestudyecommerce.product.Product;
import com.casestudyecommerce.security.model.UserPrinciple;
import com.casestudyecommerce.user.users.IUserService;
import com.casestudyecommerce.user.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private ICartService cartService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderStatusService orderStatusService;

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
        orders.setUser(user);
        OrderStatus orderStatus = orderStatusService.findById(1L).get();
        orders.setOrderStatus(orderStatus);
        orders.setDateCreated(orders.getDateCreated());
        Orders savedOrder = orderService.save(orders);
        Iterable<CartDetail> cartDetails = cartService.findAllByUser(user);
        List<OrderDetail> orderDetails = userService.convertCartDetailToOrderDetail(cartDetails, savedOrder);
        HashMap<Orders, List<OrderDetail>> ordersListHashMap = new HashMap<>();
        ordersListHashMap.put(orders, orderDetails);
        return new ResponseEntity<>(ordersListHashMap, HttpStatus.OK);
    }

    @PutMapping("/{id}/{statusId}")
    public ResponseEntity<Orders> updateOrderStatus(@PathVariable("id") Long id, @PathVariable("statusId") Long statusId) {
        Optional<Orders> optionalOrders = orderService.findById(id);
        if (!optionalOrders.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            OrderStatus orderStatus = orderStatusService.findById(statusId).get();
            Orders orders = optionalOrders.get();
            if (orderStatus.getName().equals(orders.getOrderStatus().getName())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                orders.setOrderStatus(orderStatus);
                return new ResponseEntity<>(orderService.save(orders), HttpStatus.OK);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable Long id, Authentication authentication) {
        Optional<Orders> optionalOrders = orderService.findById(id);
        if (!optionalOrders.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Orders orders = optionalOrders.get();
            if (orders.getOrderStatus().getName().equals("pending")) {
                orderService.deleteById(id);
                return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Can not delete processed order", HttpStatus.BAD_REQUEST);
            }
        }
    }

}
