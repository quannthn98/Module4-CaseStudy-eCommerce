package com.casestudyecommerce.restController.user;

import com.casestudyecommerce.cart.CartDetail;
import com.casestudyecommerce.cart.ICartService;
import com.casestudyecommerce.order.orderDetail.IOrderDetailService;
import com.casestudyecommerce.order.orderDetail.OrderDetail;
import com.casestudyecommerce.order.orders.IOrderService;
import com.casestudyecommerce.order.orders.Orders;
import com.casestudyecommerce.security.model.UserPrinciple;
import com.casestudyecommerce.user.userProfile.IUserProfileService;
import com.casestudyecommerce.user.userProfile.UserProfile;
import com.casestudyecommerce.user.users.IUserService;
import com.casestudyecommerce.user.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserProfileService userProfileService;

    @Autowired
    private ICartService cartService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<Page<User>> findAll(Pageable pageable) {
        Page<User> users = userService.findAll(pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/cart")
    public ResponseEntity<Iterable<CartDetail>> findCartByUser(Authentication authentication) {
        if (authentication == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
            User user = userService.findByUsername(userPrinciple.getUsername());
            return new ResponseEntity<>(cartService.findAllByUser(user), HttpStatus.OK);
        }
    }

    @GetMapping("/order")
    public ResponseEntity<Page<Orders>> findOrderByUser(Pageable pageable, Authentication authentication) {
        if (authentication == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            User user = userService.getUserFromJwt(authentication);
            return new ResponseEntity<>(orderService.findByUser(pageable, user), HttpStatus.OK);
        }
    }


    @GetMapping("/order/{id}")
    public ResponseEntity<Iterable<OrderDetail>> findOrderDetailByOrder(@PathVariable("id") Long id){
        Optional<Orders> optionalOrders = orderService.findById(id);
        if (!optionalOrders.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Orders order = optionalOrders.get();
        Iterable<OrderDetail> orderDetails = orderDetailService.findAllByOrders(order);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @PutMapping("/password")
    public ResponseEntity<User> changePassword(@RequestBody User user, Authentication authentication) {
        if (authentication == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            User targetUser = userService.getUserFromJwt(authentication);
            targetUser.setPassword(passwordEncoder.encode(user.getPassword()));
            return new ResponseEntity<>(targetUser, HttpStatus.OK);
        }
    }

    @PostMapping("/password/check")
    public ResponseEntity<User> checkPassword(@RequestBody String password, Authentication authentication){
        User user = userService.getUserFromJwt(authentication);
        if (passwordEncoder.matches(password, user.getPassword())) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<UserProfile> updateProfile(@RequestBody UserProfile userProfile, @PathVariable("id") Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            User user = userOptional.get();
            userProfile.setId(user.getUserProfile().getId());
            return new ResponseEntity<>(userProfileService.save(userProfile), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            userProfileService.deleteById(optionalUser.get().getUserProfile().getId());
            userService.deleteById(id);
            return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
        }
    }
}
