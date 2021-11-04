package com.casestudyecommerce.user.userProfile;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Data
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String fullName;

    @NotEmpty
    @Column(nullable = false, columnDefinition = "integer default 18")
    private int age;

    @NotEmpty
    @Column(nullable = false)
    private String address;

    @NotEmpty
    @Column(nullable = false)
    private Date birthDay;

    @NotEmpty
    @Column(nullable = false)
    private String phone;

    public UserProfile() {
    }

    public UserProfile(Long id, String fullName, int age, String address, Date birthDay, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.address = address;
        this.birthDay = birthDay;
        this.phone = phone;
    }
}
