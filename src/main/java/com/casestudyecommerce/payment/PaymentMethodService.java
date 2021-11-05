package com.casestudyecommerce.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentMethodService implements IPaymentMethodService{
    @Autowired
    private IPaymentMethodRepository paymentMethodRepository;

    @Override
    public Page<PaymentMethod> findAll(Pageable pageable) {
        return paymentMethodRepository.findAll(pageable);
    }

    @Override
    public Optional<PaymentMethod> findById(Long id) {
        return paymentMethodRepository.findById(id);
    }

    @Override
    public PaymentMethod save(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public void deleteById(Long id) {
        paymentMethodRepository.deleteById(id);
    }

    @Override
    public Page<PaymentMethod> findAllByNameContaining(String name, Pageable pageable) {
        return paymentMethodRepository.findAllByNameContaining(name,pageable);
    }

    @Override
    public Optional<PaymentMethod> findByName(String name) {
        return paymentMethodRepository.findByName(name);
    }
}
