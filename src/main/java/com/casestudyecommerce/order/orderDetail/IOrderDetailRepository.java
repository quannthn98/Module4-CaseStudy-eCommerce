package com.casestudyecommerce.order.orderDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
