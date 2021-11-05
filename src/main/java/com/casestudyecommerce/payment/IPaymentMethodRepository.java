package com.casestudyecommerce.payment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    Page<PaymentMethod> findAllByNameContaining(String name, Pageable pageable);
    Optional<PaymentMethod> findByName(String name);
}
