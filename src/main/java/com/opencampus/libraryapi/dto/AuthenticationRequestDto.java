package com.opencampus.libraryapi.dto;

/**
 * Authentication request DTO record
 */
public record AuthenticationRequestDto(String email, String password) {
}
