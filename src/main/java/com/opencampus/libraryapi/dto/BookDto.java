package com.opencampus.libraryapi.dto;

import com.opencampus.libraryapi.model.BookType;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

/**
 * Book dto record to get data from user
 * @param name
 * @param author
 * @param coverLink
 * @param isbnCode
 * @param type
 * @param publishedDate
 * @param publishingHouse
 */

public record BookDto(
        String name,
        String author,
        String coverLink,
        String isbnCode,
        BookType type,
        LocalDate publishedDate,
        String publishingHouse
){}