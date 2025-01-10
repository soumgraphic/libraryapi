package com.opencampus.libraryapi.security;

import com.opencampus.libraryapi.model.User;
import com.opencampus.libraryapi.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // RUI => ROLES : BORROWER, LIBRARIAN => ROLE_BORROWER, ROLE_LIBRARIAN
        // ADMINISTRATOR, BORROWER, LIBRARIAN

        // RBAC => ROLE BASED ACCESS CONTROL
        // -- Convert user roles to GrantedAuthority objects of spring security
        // -- This is required by spring security to check user roles in security config
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        // -- Return a new UserDetails object
        return new org.springframework.security.core.userdetails.User( // This is the building of spring boot PRINCIPAL USER
                user.getEmail(), // Email is our username
                user.getPassword(), // The password of our user
                authorities //
        );
    }
}