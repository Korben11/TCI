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
    private String category, genre, format, director, artist, publisher, isbn;
    private int year;
    private List<String> writers, stars, authors;

    public DataExtractor() {
        idCounter = 0L;
    }

    /**
     * Create Book objects from retrieved Document from crawlers & scraper
     * @param document
     */
    public List<Book> GenerateBookFromDocument(Document document) {
        bookList = new ArrayList<>();
        Elements headlines = document.select("tbody");
        for (Element headline : headlines) {
            if (headline.childNode(0).toString().contains("Books")) {
                String title = document.title();
                for (Node n : headline.childNodes()){
                    if (n.childNodes().toString().contains("Category")){
                        for (Node node : n.childNodes()){
                            if(node.toString().contains("td")){
                                category = node.childNode(0).toString();
                            }
                        }
                    }
                    else if (n.childNodes().toString().contains("Genre")){
                        for (Node node : n.childNodes()){
                            if(node.toString().contains("td")){
                                genre = node.childNode(0).toString();
                            }
                        }
                    }
                    else if (n.childNodes().toString().contains("Format")){
                        for (Node node : n.childNodes()){
                            if(node.toString().contains("td")){
                                format = node.childNode(0).toString();
                            }
                        }
                    }
                    else if (n.childNodes().toString().contains("Publisher")){
                        for (Node node : n.childNodes()){
                            if(node.toString().contains("td")){
                                publisher = node.childNode(0).toString();
                            }
                        }
                    }
                    else if (n.childNodes().toString().contains("ISBN")){
                        for (Node node : n.childNodes()){
                            if(node.toString().contains("td")){
                                isbn = node.childNode(0).toString();
                            }
                        }
                    }
                    else if (n.childNodes().toString().contains("Authors")){
                        for (Node node : n.childNodes()){
                            if(node.toString().contains("td")){
                                authors = Arrays.asList(node.childNode(0).toString().split("\\s*,\\s*"));
                            }
                        }
                    }
                    else if (n.childNodes().toString().contains("Year")){
                        for (Node node : n.childNodes()){
                            if(node.toString().contains("td")){
                                year = Integer.parseInt(node.childNode(0).toString());
                            }
                        }
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
        Elements headlines = document.select("tbody");
        for (Element headline : headlines) {
            if (headline.childNode(0).toString().contains("Music")) {
                String title = document.title();
                for (Node n : headline.childNodes()){
                    if (n.childNodes().toString().contains("Category")){
                        for (Node node : n.childNodes()){
                            if(node.toString().contains("td")){
                                category = node.childNode(0).toString();
                            }
                        }
                    }
                    else if (n.childNodes().toString().contains("Genre")){
                        for (Node node : n.childNodes()){
                            if(node.toString().contains("td")){
                                genre = node.childNode(0).toString();
                            }
                        }
                    }
                    else if (n.childNodes().toString().contains("Format")){
                        for (Node node : n.childNodes()){
                            if(node.toString().contains("td")){
                                format = node.childNode(0).toString();
                            }
                        }
                    }
                    else if (n.childNodes().toString().contains("Artist")){
                        for (Node node : n.childNodes()){
                            if(node.toString().contains("td")){
                                artist = node.childNode(0).toString();
                            }
                        }
                    }
                    else if (n.childNodes().toString().contains("Year")){
                        for (Node node : n.childNodes()){
                            if(node.toString().contains("td")){
                                year = Integer.parseInt(node.childNode(0).toString());
                            }
                        }
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
        Elements headlines = document.select("tbody");
        for (Element headline : headlines) {
            if (headline.childNode(0).toString().contains("Movies")) {
                String title = document.title();
                for (Node n : headline.childNodes()){
                    if (n.childNodes().toString().contains("Category")){
                        for (Node node : n.childNodes()){
                            if(node.toString().contains("td")){
                                category = node.childNode(0).toString();
                            }
                        }
                    }
                    else if (n.childNodes().toString().contains("Genre")){
                        for (Node node : n.childNodes()){
                            if(node.toString().contains("td")){
                                genre = node.childNode(0).toString();
                            }
                        }
                    }
                    else if (n.childNodes().toString().contains("Format")){
                        for (Node node : n.childNodes()){
                            if(node.toString().contains("td")){
                                format = node.childNode(0).toString();
                            }
                        }
                    }
                    else if (n.childNodes().toString().contains("Director")){
                        for (Node node : n.childNodes()){
                            if(node.toString().contains("td")){
                                director = node.childNode(0).toString();
                            }
                        }
                    }
                    else if (n.childNodes().toString().contains("Writers")){
                        for (Node node : n.childNodes()){
                            if(node.toString().contains("td")){
                                writers = Arrays.asList(node.childNode(0).toString().split("\\s*,\\s*"));
                            }
                        }
                    }
                    else if (n.childNodes().toString().contains("Stars")){
                        for (Node node : n.childNodes()){
                            if(node.toString().contains("td")){
                                stars = Arrays.asList(node.childNode(0).toString().split("\\s*,\\s*"));
                            }
                        }
                    }
                    else if (n.childNodes().toString().contains("Year")){
                        for (Node node : n.childNodes()){
                            if(node.toString().contains("td")){
                                year = Integer.parseInt(node.childNode(0).toString());
                            }
                        }
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
