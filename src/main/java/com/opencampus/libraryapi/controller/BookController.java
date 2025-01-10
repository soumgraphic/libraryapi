package com.opencampus.libraryapi.controller;

import com.opencampus.libraryapi.dto.ApiErrorResponse;
import com.opencampus.libraryapi.dto.BookDto;
import com.opencampus.libraryapi.dto.mapper.BookMapper;
import com.opencampus.libraryapi.model.Book;
import com.opencampus.libraryapi.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * Book controller
 */
@RestController
@Tag(name = "Books", description = "endpoints to manage books")
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @Operation(
        summary = "To get a book by its id",
        description = "You need to be authenticated"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successful",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)
                    )
            }
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized",
            content = {
                    @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiErrorResponse.class))
            }
    )
    @ApiResponse(
            responseCode = "400",
            description = "Bad request",
            content = {
                    @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiErrorResponse.class))
            }
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = {
                    @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiErrorResponse.class))
            }
    )
    @GetMapping("/{bookId}")
    Book get(@PathVariable Long bookId){
        // -- Get book by id from path variable and return it, if not found return http error not found 404
        return this.bookService.get(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasAnyRole('ROLE_BORROWER','ROLE_ADMINISTRATOR')")
    @Operation(
            summary = "To create a new book",
            description = "The role BORROWER is required"
    )
    @PostMapping
    ResponseEntity<Book> create(@RequestBody BookDto bookDto){
        // -- Mapping book dto to book
        Book bookToAdd = BookMapper.toBook(bookDto);
            // .map(ResponseEntity.status(HttpStatus.CREATED)::body)
        return this.bookService.create(bookToAdd)
                .map(ResponseEntity.status(HttpStatus.CREATED)::body)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PreAuthorize("hasRole('ROLE_BORROWER')")
    @Operation(
            summary = "To update a book",
            description = "The role BORROWER is required"
    )
    @PutMapping("/{bookId}")
    ResponseEntity<Book> update(@RequestBody BookDto bookDto, @PathVariable Long bookId){
        // -- Find book by id
        Book existingBook = this.bookService.get(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // -- Mapping book dto to existing book
        Book bookToAdd = BookMapper.toBook(bookDto);
        bookToAdd.setId(bookId);
        bookToAdd.setCreatedDate(existingBook.getCreatedDate());

        return this.bookService.update(bookToAdd)
                .map(ResponseEntity.status(HttpStatus.OK)::body)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @Operation(
            summary = "To delete a book",
            description = "The role ADMINISTRATOR is required"
    )
    @DeleteMapping("/{bookId}")
    ResponseEntity<Void> delete(@PathVariable Long bookId){
        // -- Find book by id
        Book existingBook = this.bookService.get(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // -- Delete book
        this.bookService.delete(existingBook);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
