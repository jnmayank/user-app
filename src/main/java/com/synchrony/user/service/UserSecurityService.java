package com.synchrony.user.service;

import com.synchrony.user.dto.UserPrincipal;
import com.synchrony.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username)
                .map(appUser -> new UserPrincipal(appUser))
                .orElseThrow(() -> new UsernameNotFoundException("username : " + username + "is not found"));
    }
}
