package com.model.masterdata;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Book {

    private Long id;
    private String title;
    private String genre;
    private Double price;
    private Author author;

    public Book(String title, String genre, Double price, Author author) {
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.author = author;
    }

    public Book(Long id, String title, Double price, Author author) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.author = author;
    }

    public Book(Long id, String title, String genre, Double price, Author author) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.author = author;
    }
}
