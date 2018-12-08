package service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Music {
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String category;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String genre;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String format;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String artist;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int year;

    public Music(String category, String genre, String format, String artist, int year) {
        this.category = category;
        this.genre = genre;
        this.format = format;
        this.artist = artist;
        this.year = year;
    }
}
