package com.casestudyecommerce.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService implements ICartService{
    @Autowired
    private ICartRepository cartRepository;

    @Override
    public Page<CartDetail> findAll(Pageable pageable) {
        return cartRepository.findAll(pageable);
    }

    @Override
    public Optional<CartDetail> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public CartDetail save(CartDetail cartDetail) {
        return cartRepository.save(cartDetail);
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }
}
