package service.extractors;

import org.jsoup.nodes.Document;
import service.models.Book;
import service.models.Movie;
import service.models.Music;

import java.util.ArrayList;
import java.util.List;

public class DataExtractor {

    private List<Book> bookList;
    private List<Music> musicList;
    private List<Movie> movieList;

    public DataExtractor() {
        this.bookList = new ArrayList<>();
        this.musicList = new ArrayList<>();
        this.movieList = new ArrayList<>();
    }

    public void GenerateBookFromDocument(Document document) {

    }

    public void GenerateMusicFromDocument(Document document) {

    }

    public void GenerateMovieFromDocument(Document document) {

    }

    public List<Book> getBookList() {
        return bookList;
    }

    public List<Music> getMusicList() {
        return musicList;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }
}
