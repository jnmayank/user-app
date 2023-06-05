package com.synchrony.user.service;

import com.synchrony.user.dto.UserDto;
import com.synchrony.user.entity.AppUser;

public interface IUserService {
    public AppUser saveUser(UserDto userDto);
}
