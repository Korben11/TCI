package service.models;

import java.util.List;

public class Book {
    private Long id;
    private String category;
    private String genre;
    private String format;
    private List<String> author;
    private String publisher;
    private String isbn;
    private int year;

    public Book() {
    }

    public Book(Long id, String category, String genre, String format, List<String> author, String publisher, String isbn, int year) {
        this.id = id;
        this.category = category;
        this.genre = genre;
        this.format = format;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.year = year;
    }
}
