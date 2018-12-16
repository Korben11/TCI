package service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Crawler {
    private Long id;
    private String strategy = "BFS";
    private int pages;
    private int time;
    private int depth;
    private String url;
    private ArrayList<Book> books;
    private ArrayList<Music> music;
    private ArrayList<Movie> movies;

    public Crawler() {}

    public Crawler(Long id, String strategy, int pages, int time, int depth, String url, ArrayList<Book> books, ArrayList<Music> music, ArrayList<Movie> movies) {
        this.id = id;
        this.strategy = strategy;
        this.pages = pages;
        this.time = time;
        this.depth = depth;
        this.url = url;
        this.books = books;
        this.music = music;
        this.movies = movies;
    }

}
