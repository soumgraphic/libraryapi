package com.opencampus.libraryapi.controller;

import com.opencampus.libraryapi.dto.AuthenticationRequestCreateDto;
import com.opencampus.libraryapi.dto.AuthenticationRequestDto;
import com.opencampus.libraryapi.model.User;
import com.opencampus.libraryapi.security.JwtUtil;
import com.opencampus.libraryapi.security.MyUserDetailsService;
import com.opencampus.libraryapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Map;

@RestController
@Tag(name = "Auth", description = "endpoints for authentication")
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final MyUserDetailsService userDetailsService;

    private final UserService userService;

    private final PasswordEncoder encoder;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil,
            MyUserDetailsService userDetailsService,
            UserService userService,
            PasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.encoder = encoder;
    }

    @Operation(
            summary = "Endpoint for authentication"
    )
    @PostMapping("/sign-in")
    public ResponseEntity<Map<String, String>> authenticateUser(@RequestBody AuthenticationRequestDto authenticationRequestDto) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequestDto.email(),
                            authenticationRequestDto.password()
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequestDto.email());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(Map.of("accessToken", jwt));
    }

    @Operation(
            summary = "Endpoint to create account"
    )
    @PostMapping("/signup")
    public User registerUser(@RequestBody AuthenticationRequestCreateDto authenticationRequestCreateDto) {
        // -- Check if the username is already taken and throw an exception if it is
        userService.getByUsername(authenticationRequestCreateDto.email())
                .ifPresent(user -> {
                    throw new RuntimeException("Error: user already exists, please sign in");
                });

        // -- Create a new user's account
        User newUser = new User();
        newUser.setEmail(authenticationRequestCreateDto.email());
        newUser.setPassword(encoder.encode(authenticationRequestCreateDto.password()));
        newUser.setFirstName(authenticationRequestCreateDto.firstName());
        newUser.setLastName(authenticationRequestCreateDto.lastName());
        newUser.setRoles(new HashSet<>(authenticationRequestCreateDto.roles()));

        return userService.create(newUser)
                .orElseThrow(() -> new RuntimeException("Error occurred while adding user"));
    }
}