package com.casestudyecommerce.user;

import com.casestudyecommerce.role.Role;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private Long id;

    private String username;

    @Size(min = 6, max = 12)
    private String password;

    private List<Role> roles;

    private String fullName;

    private int age;

    private String address;

    private Date birthDay;

    private String phone;
}
