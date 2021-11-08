package com.casestudyecommerce.order.orders;

import com.casestudyecommerce.user.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Orders, Long > {
    Page<Orders> findByFullNameContaining(Pageable pageable, String fullName);

    Page<Orders> findByUser(Pageable pageable, User user);

    Iterable<Orders> findByUser(User user);
}
