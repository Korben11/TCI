package service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Book {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String category;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String genre;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String format;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<String> author;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String publisher;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String isbn;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int year;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Book() {
    }

    public Book(String category, String genre, String format, List<String> author, String publisher, String isbn, int year) {
        this.category = category;
        this.genre = genre;
        this.format = format;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.year = year;
    }
}
