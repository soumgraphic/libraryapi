package com.opencampus.libraryapi.repository;

import com.opencampus.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Book repository
 */
@Repository
// -- Book is the model of our table
// -- Long is the type of our primary key
public interface BookRepository extends JpaRepository<Book, Long> {
}
