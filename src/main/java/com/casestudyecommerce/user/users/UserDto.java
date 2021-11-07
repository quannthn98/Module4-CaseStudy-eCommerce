package com.casestudyecommerce.user.users;

import com.casestudyecommerce.role.Role;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private Long id;

    @Size(min = 6, max = 18)
    private String username;

    @Size(min = 6, max = 12)
    private String password;

    private List<Role> roles;

    @Size(max = 50, min = 4)
    private String fullName;

    private int age;

    @Size(max = 255)
    private String address;

    private String birthDay;

    @Size(min = 10, max = 11)
    private String phone;
}
