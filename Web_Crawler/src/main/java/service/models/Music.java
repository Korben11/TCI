package service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Music {

    private Long id;
    private String title;
    private String category;
    private String genre;
    private String format;
    private String artist;
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

    public String getArtist() {
        return artist;
    }

    public int getYear() {
        return year;
    }

    public Music(Long id, String title, String category, String genre, String format, String artist, int year) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.genre = genre;
        this.format = format;
        this.artist = artist;
        this.year = year;
    }

    public Music() { }
}
