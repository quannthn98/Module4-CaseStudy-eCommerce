package com.casestudyecommerce.user.users;


import com.casestudyecommerce.IGeneralService;

import com.casestudyecommerce.user.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User save(UserDto userDto);

    User findByUsername(String username);

    boolean isUserDuplicated(String username);
}
