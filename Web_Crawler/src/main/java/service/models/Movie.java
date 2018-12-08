package service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Movie {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String category;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String genre;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String format;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String director;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<String> writer;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<String> star;

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

    public List<String> getWriter() {
        return writer;
    }

    public void setWriter(List<String> writer) {
        this.writer = writer;
    }

    public List<String> getStar() {
        return star;
    }

    public void setStar(List<String> star) {
        this.star = star;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Movie() {
    }

    public Movie(String category, String genre, String format, String director, List<String> writer, List<String> star, int year) {
        this.category = category;
        this.genre = genre;
        this.format = format;
        this.director = director;
        this.writer = writer;
        this.star = star;
        this.year = year;
    }
}
