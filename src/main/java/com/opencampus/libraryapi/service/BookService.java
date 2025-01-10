package com.opencampus.libraryapi.service;

import com.opencampus.libraryapi.model.Book;

import java.util.Optional;

/**
 * Book service
 */
public interface BookService {
    /**
     * Get book by id
     * @param id the book id
     * @return the book find by id
     */
    Optional<Book> get(Long id);

    /**
     * Create a new book
     * @param book the book to create
     * @return the new book created
     */
    Optional<Book> create(Book book);

    /**
     * Update existing book
     * @param book the book to update
     * @return book updated
     */
    Optional<Book> update(Book book);

    /**
     * Delete existing book
     * @param book the book to delete
     */
    void delete(Book book);
}
