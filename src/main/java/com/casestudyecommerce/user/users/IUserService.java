package com.casestudyecommerce.user.users;


import com.casestudyecommerce.IGeneralService;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByUsername(String username);

    boolean isUserDuplicated(String username);
}
