package com.casestudyecommerce.user.userStatus;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public UserStatus() {
    }

    public UserStatus(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
