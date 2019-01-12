package service.extractors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
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
    private Long idCounter;
    String category, genre, format, director, artist, publisher, isbn;
    int year;
    List<String> writers, stars, authors;
    
    public DataExtractor() {
        idCounter = 0L;
    }

    /**
     * Create Book objects from retrieved Document from crawlers & scraper
     * @param document
     */
    public List<Book> GenerateBookFromDocument(Document document) {
        musicList = new ArrayList<>();
        Elements headlines = document.select("div.media-details").select("table").select("tbody");
        for (Element headline : headlines) {
            if (headline.childNode(0).toString().contains("Music")) {
                String title = document.title();
                for (Node n : headline.childNodes()){
                    if (n.childNode(1).toString().contains("Category")){
                        category = n.childNode(3).childNode(0).toString();
                    }
                    else if (n.childNode(1).toString().contains("Genre")){
                        genre = n.childNode(3).childNode(0).toString();
                    }
                    else if (n.childNode(1).toString().contains("Format")){
                        format = n.childNode(3).childNode(0).toString();
                    }
                    else if (n.childNode(1).toString().contains("Publisher")){
                        publisher = n.childNode(3).childNode(0).toString();
                    }
                    else if (n.childNode(1).toString().contains("ISBN")){
                        isbn = n.childNode(3).childNode(0).toString();
                    }
                    else if (n.childNode(1).toString().contains("Authors")){
                        authors = Arrays.asList(n.childNode(3).childNode(0).toString().split("\\s*,\\s*"));
                    }
                    else if (n.childNode(1).toString().contains("Year")){
                        year = Integer.parseInt(n.childNode(3).childNode(0).toString());
                    }
                }
                Book book = new Book(idCounter++, title, category, genre, format, authors, publisher, isbn, year);
                bookList.add(book);
            }
        }
        return bookList;
    }

    /**
     * Create Music objects from retrieved Document from crawlers & scraper
     * @param document
     */
    public List<Music> GenerateMusicFromDocument(Document document) {
        musicList = new ArrayList<>();
        Elements headlines = document.select("div.media-details").select("table").select("tbody");
        for (Element headline : headlines) {
            if (headline.childNode(0).toString().contains("Music")) {
                String title = document.title();
                for (Node n : headline.childNodes()){
                    if (n.childNode(1).toString().contains("Category")){
                        category = n.childNode(3).childNode(0).toString();
                    }
                    else if (n.childNode(1).toString().contains("Genre")){
                        genre = n.childNode(3).childNode(0).toString();
                    }
                    else if (n.childNode(1).toString().contains("Format")){
                        format = n.childNode(3).childNode(0).toString();
                    }
                    else if (n.childNode(1).toString().contains("Artist")){
                        artist = n.childNode(3).childNode(0).toString();
                    }
                    else if (n.childNode(1).toString().contains("Year")){
                        year = Integer.parseInt(n.childNode(3).childNode(0).toString());
                    }
                }
                Music music = new Music(idCounter++, title, category, genre, format, artist, year);
                musicList.add(music);
            }
        }
        return musicList;
    }

    /**
     * Create Movie objects from retrieved Document from crawlers & scraper
     * @param document
     */
    public List<Movie> GenerateMovieFromDocument(Document document) {
        movieList = new ArrayList<>();
        Elements headlines = document.select("div.media-details").select("table").select("tbody");
        for (Element headline : headlines) {
            if (headline.childNode(0).toString().contains("Book")) {
                String title = document.title();

                for (Node n : headline.childNodes()){
                    if (n.childNode(1).toString().contains("Category")){
                        category = n.childNode(3).childNode(0).toString();
                    }
                    else if (n.childNode(1).toString().contains("Genre")){
                        genre = n.childNode(3).childNode(0).toString();
                    }
                    else if (n.childNode(1).toString().contains("Format")){
                        format = n.childNode(3).childNode(0).toString();
                    }
                    else if (n.childNode(1).toString().contains("Director")){
                        director = n.childNode(3).childNode(0).toString();
                    }
                    else if (n.childNode(1).toString().contains("Year")){
                        year = Integer.parseInt(n.childNode(3).childNode(0).toString());
                    }
                    else if (n.childNode(1).toString().contains("Writers")){
                        writers = Arrays.asList(n.childNode(3).childNode(0).toString().split("\\s*,\\s*"));
                    }
                    else if (n.childNode(1).toString().contains("Stars")){
                        stars = Arrays.asList(n.childNode(3).childNode(0).toString().split("\\s*,\\s*"));
                    }
                }
                Movie movie = new Movie(idCounter++, title, category, genre, format, director, writers, stars, year);
                movieList.add(movie);
            }
        }
        return movieList;
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
