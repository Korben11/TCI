package service.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.crawlers.BFSCrawler;
import service.crawlers.NextPageDoesNotExistsException;
import service.extractors.DataExtractor;
import service.models.Book;
import service.models.Movie;
import service.models.Music;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CrawlerController {
    private static final String pathToBooks = "/books";
    private static final String pathToMusic = "/music";
    private static final String pathToMovies = "/movies";
    private static final String pathToABook = pathToBooks + "/{id}";
    private static final String pathToASong = pathToMusic + "/{id}";
    private static final String pathToAMovie = pathToMovies + "/{id}";
    private DataExtractor dataExtractor = new DataExtractor();
    ObjectMapper mapper;
    BFSCrawler bfsCrawler;


    @GetMapping(pathToBooks)
    public List GetAllBooksJson() throws IOException, NextPageDoesNotExistsException {
        ArrayList<String> bookJSONList = new ArrayList<>();
        bfsCrawler = new BFSCrawler("http://i296827.hera.fhict.nl", Jsoup.connect("http://i296827.hera.fhict.nl"));
        while(bfsCrawler.hasNext()){
            Document document = bfsCrawler.next();
            for (Element e : document.select("div.media-details")) {
                for(Node n:e.childNodes()) {
                    if (n.toString().contains("Books")) {
                        bookJSONList.add(ConvertBookObjectToJson(dataExtractor.GenerateBookFromDocument(document)));
                        break;
                    }
                }
            }
        }
        return bookJSONList;
    }

    @GetMapping(pathToMusic)
    public List GetAllMusicJson() throws IOException, NextPageDoesNotExistsException {
        ArrayList<String> musicJSONList = new ArrayList<>();
        bfsCrawler = new BFSCrawler("http://i296827.hera.fhict.nl", Jsoup.connect("http://i296827.hera.fhict.nl"));
        while(bfsCrawler.hasNext()){
            Document document = bfsCrawler.next();
            for (Element e : document.select("div.media-details")) {
                for(Node n:e.childNodes()) {
                    if (n.toString().contains("Music")) {
                        musicJSONList.add(ConvertMusicObjectToJson(dataExtractor.GenerateMusicFromDocument(document)));
                        break;
                    }
                }
            }
        }
        return musicJSONList;
    }

    @GetMapping(pathToMovies)
    public List GetAllMoviesJson() throws IOException, NextPageDoesNotExistsException {
        ArrayList<String> movieJSONList = new ArrayList<>();
        bfsCrawler = new BFSCrawler("http://i296827.hera.fhict.nl", Jsoup.connect("http://i296827.hera.fhict.nl"));
        while(bfsCrawler.hasNext()){
            Document document = bfsCrawler.next();
            for (Element e : document.select("div.media-details")) {
                for(Node n:e.childNodes()) {
                    if (n.toString().contains("Movie")) {
                        movieJSONList.add(ConvertMovieObjectToJson(dataExtractor.GenerateMovieFromDocument(document)));
                        break;
                    }
                }
            }
        }
        return movieJSONList;
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
        String s = mapper.writeValueAsString(b);
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
        String s = mapper.writeValueAsString(m);
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
        String s = mapper.writeValueAsString(m);
        return s;
    }



}
