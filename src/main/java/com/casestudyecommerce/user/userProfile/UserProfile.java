package com.casestudyecommerce.user.userProfile;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    @Size(max = 50)
    private String fullName;

    @Column(columnDefinition = "integer default 18")
    private int age;

    @NotEmpty
    @Column(nullable = false)
    @Size(max = 255)
    private String address;

    private String birthDay;

    @NotEmpty
    @Column(nullable = false)
    @Size(min = 10, max = 11)
    private String phone;

    public UserProfile() {
    }

    public UserProfile(Long id, String fullName, int age, String address, String birthDay, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.address = address;
        this.birthDay = birthDay;
        this.phone = phone;
    }

    public UserProfile(String fullName, int age, String address, String birthDay, String phone) {
        this.fullName = fullName;
        this.age = age;
        this.address = address;
        this.birthDay = birthDay;
        this.phone = phone;
    }
}
