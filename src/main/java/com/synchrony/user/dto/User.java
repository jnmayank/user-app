package com.synchrony.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String roles;
}
