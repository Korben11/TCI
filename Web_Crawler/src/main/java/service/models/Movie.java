package service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Movie {

    private Long id;
    private String title;
    private String category;
    private String genre;
    private String format;
    private String director;
    private List<String> writer;
    private List<String> star;
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

    public String getDirector() {
        return director;
    }

    public List<String> getWriter() {
        return writer;
    }

    public List<String> getStar() {
        return star;
    }

    public int getYear() {
        return year;
    }

    public Movie() {
    }

    public Movie(Long id, String title, String category, String genre, String format, String director, List<String> writer, List<String> star, int year) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.genre = genre;
        this.format = format;
        this.director = director;
        this.writer = writer;
        this.star = star;
        this.year = year;
    }
}
