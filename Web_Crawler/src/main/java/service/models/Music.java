package service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Music {
    private Long id;
    private String category;
    private String genre;
    private String format;
    private String artist;
    private int year;

    public Music(Long id, String category, String genre, String format, String artist, int year) {
        this.id = id;
        this.category = category;
        this.genre = genre;
        this.format = format;
        this.artist = artist;
        this.year = year;
    }

    public Music() { }
}
