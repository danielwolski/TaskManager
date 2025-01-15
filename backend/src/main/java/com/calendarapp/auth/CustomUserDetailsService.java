package com.calendarapp.auth;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder;

    public CustomUserDetailsService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("received {}", username);
        if ("admin".equals(username)) {

            String hashedPassword = passwordEncoder.encode("admin");

            return new User("admin", hashedPassword, Collections.emptyList());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
