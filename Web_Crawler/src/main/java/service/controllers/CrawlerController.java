package service.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.extractors.DataExtractor;
import service.models.Book;
import service.models.Movie;
import service.models.Music;

import java.util.ArrayList;
import java.util.List;

public class CrawlerController {
    private static final String pathToBooks = "/books";
    private static final String pathToMusic = "/music";
    private static final String pathToMovies = "/movies";
    private static final String pathToABook = pathToBooks + "/{id}";
    private static final String pathToASong = pathToMusic + "/{id}";
    private static final String pathToAMovie = pathToMovies + "/{id}";
    ObjectMapper mapper;
    private DataExtractor dataExtractor = new DataExtractor();
    //CrawlerObject
    //return document

    //DataExtractor
    //return book/music/movie from document object

    @GetMapping(pathToBooks)
    public List GetAllBooks() throws JsonProcessingException {
//        ArrayList<String> bookJSONList = new ArrayList<>();
//        List<Book> books = dataExtractor.getBookList();
//        for (Book b : books) {
//            bookJSONList.add(ConvertBookObjectToJson(b));
//        }
//        return bookJSONList;
        return null;
    }

    @GetMapping(pathToMusic)
    public List GetAllMuic() throws JsonProcessingException {
//        ArrayList<String> musicJSONList = new ArrayList<>();
//        List<Music> music = dataExtractor.getMusicList();
//        for (Music m : music) {
//            musicJSONList.add(ConvertMusicObjectToJson(m));
//        }
//        return musicJSONList;
        return null;
    }

    @GetMapping(pathToMovies)
    public List GetAllMovies() throws JsonProcessingException {
//        ArrayList<String> movieJSONList = new ArrayList<>();
//        List<Movie> movies = dataExtractor.getMovieList();
//        for (Movie m : movies) {
//            movieJSONList.add(ConvertMovieObjectToJson(m));
//        }
//        return movieJSONList;
        return null;
    }
    //EndPoint method
    @GetMapping(pathToABook)
    public List GetABook()
    {
        return null;
    }

    @GetMapping(pathToASong)
    public List GetASong()
    {
        return null;
    }

    @GetMapping(pathToAMovie)
    public List GetAMovie()
    {
        return null;
    }

    /**
     * Convert a book object into a JSON format string
     * @param b book object
     * @return string
     * @throws JsonProcessingException
     */
    public String ConvertBookObjectToJson(Book b) throws JsonProcessingException {
        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        String s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(b);
        return s;
    }

    /**
     * Convert a movie object into a JSON format string
     * @param m movie object
     * @return string
     * @throws JsonProcessingException
     */
    public String ConvertMovieObjectToJson(Movie m) throws JsonProcessingException {
        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        String s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(m);
        return s;
    }

    /**
     * Convert a music object into a JSON format string
     * @param m music object
     * @return string
     * @throws JsonProcessingException
     */
    public String ConvertMusicObjectToJson(Music m) throws JsonProcessingException {
        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        String s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(m);
        return s;
    }



}
