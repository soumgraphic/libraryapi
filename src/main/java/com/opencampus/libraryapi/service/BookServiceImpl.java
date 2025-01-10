package com.opencampus.libraryapi.service;

import com.opencampus.libraryapi.model.Book;
import com.opencampus.libraryapi.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<Book> get(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> create(Book book) {
        // -- Set create and update date to now
        book.setCreatedDate(LocalDate.now());
        book.setUpdatedDate(LocalDate.now());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> update(Book book) {
        // -- Set book update date to now
        book.setUpdatedDate(LocalDate.now());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void delete(Book book) {
        this.bookRepository.delete(book);
    }
}
