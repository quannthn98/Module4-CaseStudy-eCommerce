package com.casestudyecommerce.payment;

import com.casestudyecommerce.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPaymentMethodService extends IGeneralService<PaymentMethod> {
    Page<PaymentMethod> findAllByNameContaining(String name, Pageable pageable);
    Optional<PaymentMethod> findByName(String name);
}
