package com.opencampus.libraryapi.dto.mapper;

import com.opencampus.libraryapi.dto.BookDto;
import com.opencampus.libraryapi.model.Book;

/**
 * Book mapper
 */
public class BookMapper {
    public static Book toBook(BookDto bookDto){
        Book book = new Book();

        // New in java no get or set to get field from object, we can get via name like below
        book.setName(bookDto.name());
        book.setAuthor(bookDto.author());
        book.setCoverLink(bookDto.coverLink());
        book.setIsbnCode(bookDto.isbnCode());
        book.setType(bookDto.type());
        book.setPublishedDate(bookDto.publishedDate());
        book.setPublishingHouse(bookDto.publishingHouse());

        return book;
    }
}
