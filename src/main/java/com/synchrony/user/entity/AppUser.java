package com.synchrony.user.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@Entity
@Table(name = "app-user")
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "user_name", unique = true)
    private String userName;
    private String password;
    private String roles;
    @OneToMany(mappedBy = "user")
    private List<Image> images;

    public AppUser(String firstName, String lastName, String userName, String password, String roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }
}
