package com.opencampus.libraryapi.model;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Book Model
 */
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private String coverLink;
    private String isbnCode;

    @Enumerated(EnumType.STRING)
    private BookType type;

    private LocalDate publishedDate;
    private String publishingHouse;
    private LocalDate createdDate;
    private LocalDate updatedDate;

    // -- Required by Spring data JPA
    public Book(){
    }

    public Book(Long id, String name, String author, String coverLink, String isbnCode, BookType type, LocalDate publishedDate, String publishingHouse, LocalDate createdDate, LocalDate updatedDate) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.coverLink = coverLink;
        this.isbnCode = isbnCode;
        this.type = type;
        this.publishedDate = publishedDate;
        this.publishingHouse = publishingHouse;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverLink() {
        return coverLink;
    }

    public void setCoverLink(String coverLink) {
        this.coverLink = coverLink;
    }

    public String getIsbnCode() {
        return isbnCode;
    }

    public void setIsbnCode(String isbnCode) {
        this.isbnCode = isbnCode;
    }

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }
}
