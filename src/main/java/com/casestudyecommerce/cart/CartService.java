package com.casestudyecommerce.cart;

import com.casestudyecommerce.user.users.IUserService;
import com.casestudyecommerce.user.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService implements ICartService{
    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IUserService userService;

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
        User user = cartDetail.getUser();
        Iterable<CartDetail> cartDetails = cartRepository.findAllByUser(user);
        for (CartDetail c: cartDetails){
            if (c.getProduct().getId() == cartDetail.getProduct().getId()){
                cartDetail.setId(c.getId());
                cartDetail.setQuantity(c.getQuantity() + cartDetail.getQuantity());
                break;
            }
        }
        return cartRepository.save(cartDetail);
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Iterable<CartDetail> findAllByUser(User user) {
        return cartRepository.findAllByUser(user);
    }

    @Override
    public CartDetail updateQuantity(CartDetail cartDetail) {
        return cartRepository.save(cartDetail);
    }

}
