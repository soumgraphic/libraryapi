package com.opencampus.libraryapi.dto;

import com.opencampus.libraryapi.model.UserType;

import java.util.List;

/**
 * Authentication request DTO record
 */
public record AuthenticationRequestCreateDto(String firstName, String lastName, String email, String password, List<UserType> roles) {
}
