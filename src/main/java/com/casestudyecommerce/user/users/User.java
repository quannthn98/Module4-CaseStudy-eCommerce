package com.casestudyecommerce.user.users;

import com.casestudyecommerce.role.Role;
import com.casestudyecommerce.user.userProfile.UserProfile;
import com.casestudyecommerce.user.userStatus.UserStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 6, max = 18)
    @Column(nullable = false, unique = true)
    private String username;

    @NotEmpty
    @Column(nullable = false, columnDefinition = "text")
    private String password;

    @ManyToMany
    private List<Role> roles;

    @OneToOne(fetch = FetchType.EAGER)
    private UserProfile userProfile;

    @ManyToOne
    private UserStatus userStatus;

    public User() {
    }

    public User(Long id, String username, String password, List<Role> roles, UserProfile userProfile, UserStatus userStatus) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.userProfile = userProfile;
        this.userStatus = userStatus;
    }

    public User(String username, String password, List<Role> roles, UserProfile userProfile, UserStatus userStatus) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.userProfile = userProfile;
        this.userStatus = userStatus;
    }
}
