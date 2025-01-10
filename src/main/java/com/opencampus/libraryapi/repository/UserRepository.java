package com.opencampus.libraryapi.repository;

import com.opencampus.libraryapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    User findUserByFirstName(String firstName);
}
