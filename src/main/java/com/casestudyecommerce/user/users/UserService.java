package com.casestudyecommerce.user.users;

import com.casestudyecommerce.cart.CartDetail;
import com.casestudyecommerce.order.orderDetail.IOrderDetailService;
import com.casestudyecommerce.order.orderDetail.OrderDetail;
import com.casestudyecommerce.order.orders.Orders;
import com.casestudyecommerce.product.Product;
import com.casestudyecommerce.role.Role;
import com.casestudyecommerce.security.model.UserPrinciple;
import com.casestudyecommerce.user.UserDto;
import com.casestudyecommerce.user.userProfile.IUserProfileService;
import com.casestudyecommerce.user.userProfile.UserProfile;
import com.casestudyecommerce.user.userStatus.IUserStatusService;
import com.casestudyecommerce.user.userStatus.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    @Autowired
    IUserRepository userRepository;

    @Autowired
    private IUserStatusService userStatusService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Autowired
    private IUserProfileService userProfileService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User save(UserDto userDto) {
        String username = userDto.getUsername();
        String password = passwordEncoder.encode(userDto.getPassword());
        List<Role> roles = userDto.getRoles();
        String fullName = userDto.getFullName();
        int age = userDto.getAge();
        String address = userDto.getAddress();
        Date birthDay = userDto.getBirthDay();
        String phone = userDto.getPhone();
        UserStatus userStatus = userStatusService.findById(1L).get();
        UserProfile userProfile = new UserProfile(fullName, age, address, birthDay, phone);
        User user = new User(username, password, roles, userProfile, userStatus);
        userProfileService.save(userProfile);
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return UserPrinciple.build(user);
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean isUserDuplicated(String username) {
        User optionalUser = userRepository.findByUsername(username);
        if (optionalUser == null){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<OrderDetail> convertCartDetailToOrderDetail(Iterable<CartDetail> cartDetails, Orders orders) {
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

    @Override
    public User getUserFromJwt(Authentication authentication) {
        if (authentication == null){
            return null;
        } else {
            UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
            User user = findByUsername(userPrinciple.getUsername());
            return user;
        }
    }
}
