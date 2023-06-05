package com.synchrony.user.service;

import com.synchrony.user.dto.User;
import com.synchrony.user.entity.AppUser;

public interface IUserService {
    AppUser saveUser(User userDto);
    AppUser fetchUserByID(int id);
}
