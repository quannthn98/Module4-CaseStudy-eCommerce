package com.casestudyecommerce.order.orders;

import com.casestudyecommerce.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService extends IGeneralService<Orders> {
    Page<Orders> findByFullNameContaining(Pageable pageable, String fullName);
}
