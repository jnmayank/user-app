package com.synchrony.user.service;

import com.synchrony.user.dto.User;
import com.synchrony.user.entity.AppUser;
import com.synchrony.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService implements IUserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser saveUser(User userDto) {
        AppUser appUser = new AppUser(userDto.getFirstName(), userDto.getLastName(), userDto.getUserName(),
                passwordEncoder.encode(userDto.getPassword()) , userDto.getRoles());
        return userRepository.save(appUser);
    }

    public AppUser fetchUser(String useName) {
        return userRepository.findByUserName(useName).orElseThrow(() ->
                new UsernameNotFoundException("username : " + useName + "is not found"));
    }

    public AppUser fetchUserByID(int id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("user with id : " + id + "is not found"));
    }
}
