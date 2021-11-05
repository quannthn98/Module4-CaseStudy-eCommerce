package com.casestudyecommerce.cart;

import com.casestudyecommerce.IGeneralService;
import com.casestudyecommerce.user.users.User;

public interface ICartService extends IGeneralService<CartDetail> {
    Iterable<CartDetail> findByUser(User user);

}
