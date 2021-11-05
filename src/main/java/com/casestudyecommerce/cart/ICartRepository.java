package com.casestudyecommerce.cart;

import com.casestudyecommerce.user.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends JpaRepository<CartDetail, Long> {
    Iterable<CartDetail> findAllByUser(User user);
}
