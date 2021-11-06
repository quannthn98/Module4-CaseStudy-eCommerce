package com.casestudyecommerce.user.users;


import com.casestudyecommerce.IGeneralService;

import com.casestudyecommerce.cart.CartDetail;
import com.casestudyecommerce.order.orderDetail.OrderDetail;
import com.casestudyecommerce.order.orders.Orders;
import com.casestudyecommerce.user.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User save(UserDto userDto);

    User findByUsername(String username);

    boolean isUserDuplicated(String username);

    List<OrderDetail> convertCartDetailToOrderDetail(Iterable<CartDetail> cartDetails, Orders orders);

    User getUserFromJwt(Authentication authentication);
}
