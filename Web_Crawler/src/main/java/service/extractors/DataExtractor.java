package service.extractors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import service.models.Book;
import service.models.Movie;
import service.models.Music;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class helps collecting data retrieve from the web crawler and scraper
 * as files of type Jsoup.Document
 * and convert them to Book/Movie/Music objects
 */
public class DataExtractor {

    private List<Book> bookList;
    private List<Music> musicList;
    private List<Movie> movieList;

    public DataExtractor() {
        this.bookList = new ArrayList<>();
        this.musicList = new ArrayList<>();
        this.movieList = new ArrayList<>();
    }

    /**
     * Create Book objects from retrieved Document from crawlers & scraper
     * @param document
     */
    public Book GenerateBookFromDocument(Document document) {
//        Elements headlines = document.select("div.media-details");
//        for (Element headline : headlines) {
//            if(headline.tagName() == "test"){
//                return new Book();
//            }
//        }
        return null;
    }

    /**
     * Create Music objects from retrieved Document from crawlers & scraper
     * @param document
     */
    public Music GenerateMusicFromDocument(Document document) {
        return null;
    }

    /**
     * Create Movie objects from retrieved Document from crawlers & scraper
     * @param document
     */
    public Movie GenerateMovieFromDocument(Document document) {
        return null;
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
