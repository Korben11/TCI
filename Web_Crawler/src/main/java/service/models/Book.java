package service.models;

import java.util.List;

public class Book {
    
    private Long id;
    private String title;
    private String category;
    private String genre;
    private String format;
    private List<String> author;
    private String publisher;
    private String isbn;
    private int year;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getGenre() {
        return genre;
    }

    public String getFormat() {
        return format;
    }

    public List<String> getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getYear() {
        return year;
    }

    public Book() {
    }

    public Book(Long id, String title, String category, String genre, String format, List<String> author, String publisher, String isbn, int year) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.genre = genre;
        this.format = format;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.year = year;
    }
}
