package service.controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import service.extractors.DataExtractor;
import service.models.Book;
import service.models.Movie;
import service.models.Music;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;


public class CrawlerControllerTest {
//    CrawlerController crawlerController = new CrawlerController();
    /**
     * Mock an object of class DataExtractor
     */
    @Mock
    private DataExtractor dataExtractor;

    /**
     * Inject the mock object to the object to test so it can be used in CrawlerController class
     */
    @InjectMocks
    private CrawlerController crawlerController;

    /**
     * Initial the mock before every test being executed
     */
    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    /**
     * This test check if the method GetAllBooksJson returns the correct json list which is converted from a book list
     * It uses a mock object "dataExtractor" provides a book list by calling the "getBookList()" method
     * The object to test "crawlerController" uses dataExtractor.getBookList() to get all books then convert them to json
     * @throws JsonProcessingException
     */
    @Test
    public void GetAllBooksJsonShouldReturnListOfAllBookInJson() throws JsonProcessingException {
        //Arrange
        Book book = new Book(1L,
                "FlyWithMe",
                "Books",
                "Tech",
                "Ebook",
                Arrays.asList("Robert C. Martin"),
                "Prentice Hall",
                "978-013235-0884",
                2008);
        String expectedJson = "{\n" +
                "  \"id\" : 1,\n" +
                "  \"title\" : \"FlyWithMe\",\n" +
                "  \"category\" : \"Books\",\n" +
                "  \"genre\" : \"Tech\",\n" +
                "  \"format\" : \"Ebook\",\n" +
                "  \"author\" : [ \"Robert C. Martin\" ],\n" +
                "  \"publisher\" : \"Prentice Hall\",\n" +
                "  \"isbn\" : \"978-013235-0884\",\n" +
                "  \"year\" : 2008\n" +
                "}";
        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);
        //Act
        when(dataExtractor.getBookList()).thenReturn(bookList);
        //Assert
        Assert.assertEquals(bookList,crawlerController.GetAllBooksJson());
    }

    /**
     * This test check if the method GetAllMusicJson returns the correct json list which is converted from a music list
     * It uses a mock object "dataExtractor" provides a music list by calling the "getMusicList()" method
     * The object to test "crawlerController" uses dataExtractor.getMusicList() to get all music then convert them to json
     * @throws JsonProcessingException
     */
    @Test
    public void GetAllMusicJsonShouldReturnListOfAllBookInJson() throws JsonProcessingException {
        //Arrange
        Music music = new Music(1L,
                "The old one",
                "Music",
                "Classical",
                "CD",
                "Ludwig van Beethoven",
                2012);
        String expectedJson = "{\n" +
                "  \"id\" : 1,\n" +
                "  \"title\" : \"The old one\",\n" +
                "  \"category\" : \"Music\",\n" +
                "  \"genre\" : \"Classical\",\n" +
                "  \"format\" : \"CD\",\n" +
                "  \"artist\" : \"Ludwig van Beethoven\",\n" +
                "  \"year\" : 2012\n" +
                "}";
        ArrayList<Music> musicList = new ArrayList<>();
        musicList.add(music);
        //Act
        when(dataExtractor.getMusicList()).thenReturn(musicList);
        //Assert
        Assert.assertEquals(musicList,crawlerController.GetAllMusicJson());
    }

    /**
     * This test check if the method GetAllMoviesJson returns the correct json list which is converted from a movie list
     * It uses a mock object "dataExtractor" provides a movie list by calling the "getMovieList()" method
     * The object to test "crawlerController" uses dataExtractor.getMovieList() to get all movies then convert them to json
     * @throws JsonProcessingException
     */
    @Test
    public void GetAllMoviesJsonShouldReturnListOfAllBookInJson() throws JsonProcessingException {
        //Arrange
        Movie movie = new Movie(1L,
                "Forest",
                "Forest Gump",
                "Drama",
                "DVD",
                "Robert Zemeckis",
                Arrays.asList("Winston Groom", "Eric Roth"),
                Arrays.asList("Tom Hanks", "Rebecca Williams", "Sally Field", "Michael Conner Humphreys"),
                1994);
        String expectedJson = "{\n" +
                "  \"id\" : 1,\n" +
                "  \"title\" : \"Forest\",\n" +
                "  \"category\" : \"Forest Gump\",\n" +
                "  \"genre\" : \"Drama\",\n" +
                "  \"format\" : \"DVD\",\n" +
                "  \"director\" : \"Robert Zemeckis\",\n" +
                "  \"writer\" : [ \"Winston Groom\", \"Eric Roth\" ],\n" +
                "  \"star\" : [ \"Tom Hanks\", \"Rebecca Williams\", \"Sally Field\", \"Michael Conner Humphreys\" ],\n" +
                "  \"year\" : 1994\n" +
                "}";
        ArrayList<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        //Act
        when(dataExtractor.getMovieList()).thenReturn(movieList);
        //Assert
        Assert.assertEquals(movieList,crawlerController.GetAllMoviesJson());
    }

    /**
     * This test check if the method ConvertBookObjectToJson returns the correct json format string which is converted from a string
     * @throws JsonProcessingException
     */
    @Test
    public void ConvertBookObjectToJsonShouldReturnAStringWithJsonFormat() throws JsonProcessingException {
        //Arrange
        Book book = new Book(1L,
                "FlyWithMe",
                "Books",
                "Tech",
                "Ebook",
                Arrays.asList("Robert C. Martin"),
                "Prentice Hall",
                "978-013235-0884",
                2008);
        String expectedJson = "{\n" +
                "  \"id\" : 1,\n" +
                "  \"title\" : \"FlyWithMe\",\n" +
                "  \"category\" : \"Books\",\n" +
                "  \"genre\" : \"Tech\",\n" +
                "  \"format\" : \"Ebook\",\n" +
                "  \"author\" : [ \"Robert C. Martin\" ],\n" +
                "  \"publisher\" : \"Prentice Hall\",\n" +
                "  \"isbn\" : \"978-013235-0884\",\n" +
                "  \"year\" : 2008\n" +
                "}";
        //Act
        String s = crawlerController.ConvertBookObjectToJson(book);
        //Assert
        Assert.assertEquals(expectedJson,s);
    }

    /**
     * This test check if the method ConvertMusicObjectToJson returns the correct json format string which is converted from a string
     * @throws JsonProcessingException
     */
    @Test
    public void ConvertMusicObjectToJsonShouldReturnAStringWithJsonFormat() throws JsonProcessingException {
        //Arrange
        Music music = new Music(1L,
                "The old one",
                "Music",
                "Classical",
                "CD",
                "Ludwig van Beethoven",
                2012);
        String expectedJson = "{\n" +
                "  \"id\" : 1,\n" +
                "  \"title\" : \"The old one\",\n" +
                "  \"category\" : \"Music\",\n" +
                "  \"genre\" : \"Classical\",\n" +
                "  \"format\" : \"CD\",\n" +
                "  \"artist\" : \"Ludwig van Beethoven\",\n" +
                "  \"year\" : 2012\n" +
                "}";
        //Act
        String s = crawlerController.ConvertMusicObjectToJson(music);
        //Assert
        Assert.assertEquals(expectedJson,s);
    }

    /**
     * This test check if the method ConvertMovieObjectToJson returns the correct json format string which is converted from a string
     * @throws JsonProcessingException
     */
    @Test
    public void ConvertMovieObjectToJsonShouldReturnAStringWithJsonFormat() throws JsonProcessingException {
        //Arrange
        Movie movie = new Movie(1L,
                "Forest",
                "Forest Gump",
                "Drama",
                "DVD",
                "Robert Zemeckis",
                Arrays.asList("Winston Groom", "Eric Roth"),
                Arrays.asList("Tom Hanks", "Rebecca Williams", "Sally Field", "Michael Conner Humphreys"),
                1994);
        String expectedJson = "{\n" +
                "  \"id\" : 1,\n" +
                "  \"title\" : \"Forest\",\n" +
                "  \"category\" : \"Forest Gump\",\n" +
                "  \"genre\" : \"Drama\",\n" +
                "  \"format\" : \"DVD\",\n" +
                "  \"director\" : \"Robert Zemeckis\",\n" +
                "  \"writer\" : [ \"Winston Groom\", \"Eric Roth\" ],\n" +
                "  \"star\" : [ \"Tom Hanks\", \"Rebecca Williams\", \"Sally Field\", \"Michael Conner Humphreys\" ],\n" +
                "  \"year\" : 1994\n" +
                "}";
        //Act
        String s = crawlerController.ConvertMovieObjectToJson(movie);
        //Assert
        Assert.assertEquals(expectedJson,s);
    }

}