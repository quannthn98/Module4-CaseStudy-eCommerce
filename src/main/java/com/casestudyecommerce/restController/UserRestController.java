package com.casestudyecommerce.restController;

import com.casestudyecommerce.execptionHandler.Exception.DuplicateException;
import com.casestudyecommerce.role.Role;
import com.casestudyecommerce.user.UserDto;
import com.casestudyecommerce.user.userProfile.IUserProfileService;
import com.casestudyecommerce.user.userProfile.UserProfile;
import com.casestudyecommerce.user.users.IUserService;
import com.casestudyecommerce.user.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserProfileService userProfileService;

    @GetMapping
    public ResponseEntity<Page<User>> findAll(Pageable pageable){
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody UserDto userDto) throws DuplicateException {
        if (userService.isUserDuplicated(userDto.getUsername())){
            throw new DuplicateException();
        }
        return new ResponseEntity<>(userService.save(userDto), HttpStatus.OK);
    }
}
