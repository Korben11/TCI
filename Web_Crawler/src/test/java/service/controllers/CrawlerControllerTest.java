package service.controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.crawlers.BFSCrawler;
import service.crawlers.NextPageDoesNotExistsException;
import service.models.Book;
import service.models.Movie;
import service.models.Music;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CrawlerControllerTest {
    @Mock
    private BFSCrawler bfsCrawler;

    @Mock
    private Document document;



    @InjectMocks
    private CrawlerController crawlerController;

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
    public void GetAllBooksJsonShouldReturnListOfAllBookInJson() throws IOException, NextPageDoesNotExistsException {

        //Arrange
        Element element = new Element("tbody");
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Category"))
                .appendChild(new Element("td").append("Books")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Genre"))
                .appendChild(new Element("td").append("Tech")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Format"))
                .appendChild(new Element("td").append("Audio")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Year"))
                .appendChild(new Element("td").append("2011")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Authors"))
                .appendChild(new Element("td").append("Robert C. Martin")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Publisher"))
                .appendChild(new Element("td").append("Prentice Hall")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("ISBN"))
                .appendChild(new Element("td").append("007-6092046981")));
        Elements elements = new Elements();
        elements.add(element);
        String expectedJson = "Books";
        when(bfsCrawler.hasNext()).thenReturn(true);
        when(document.select("div.media-details")).thenReturn(elements);

        //Act
        List result = crawlerController.GetAllBooksJson();

        //Assert
        Assert.assertTrue(result.get(0).toString().contains(expectedJson));
    }

    /**
     * This test check if the method GetAllMusicJson returns the correct json list which is converted from a music list
     * It uses a mock object "dataExtractor" provides a music list by calling the "getMusicList()" method
     * The object to test "crawlerController" uses dataExtractor.getMusicList() to get all music then convert them to json
     * @throws JsonProcessingException
     */
    @Test
    public void GetAllMusicJsonShouldReturnListOfAllBookInJson() throws IOException, NextPageDoesNotExistsException {
        //Arrange
        Element element = new Element("tbody");
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Category"))
                .appendChild(new Element("td").append("Music")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Genre"))
                .appendChild(new Element("td").append("Tech")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Format"))
                .appendChild(new Element("td").append("Audio")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Year"))
                .appendChild(new Element("td").append("2011")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Authors"))
                .appendChild(new Element("td").append("Robert C. Martin")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Publisher"))
                .appendChild(new Element("td").append("Prentice Hall")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("ISBN"))
                .appendChild(new Element("td").append("007-6092046981")));
        Elements elements = new Elements();
        elements.add(element);
        String expectedJson = "Music";
        when(bfsCrawler.hasNext()).thenReturn(true);
        when(document.select("div.media-details")).thenReturn(elements);

        //Act
        List result = crawlerController.GetAllMusicJson();

        //Assert
        Assert.assertTrue(result.get(0).toString().contains(expectedJson));
    }

    /**
     * This test check if the method GetAllMoviesJson returns the correct json list which is converted from a movie list
     * It uses a mock object "dataExtractor" provides a movie list by calling the "getMovieList()" method
     * The object to test "crawlerController" uses dataExtractor.getMovieList() to get all movies then convert them to json
     * @throws JsonProcessingException
     */
    @Test
    public void GetAllMoviesJsonShouldReturnListOfAllBookInJson() throws IOException, NextPageDoesNotExistsException {
        //Arrange
        Element element = new Element("tbody");
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Category"))
                .appendChild(new Element("td").append("Movies")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Genre"))
                .appendChild(new Element("td").append("Tech")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Format"))
                .appendChild(new Element("td").append("Audio")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Year"))
                .appendChild(new Element("td").append("2011")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Authors"))
                .appendChild(new Element("td").append("Robert C. Martin")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Publisher"))
                .appendChild(new Element("td").append("Prentice Hall")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("ISBN"))
                .appendChild(new Element("td").append("007-6092046981")));
        Elements elements = new Elements();
        elements.add(element);
        String expectedJson = "Movies";
        when(bfsCrawler.hasNext()).thenReturn(true);
        when(document.select("div.media-details")).thenReturn(elements);

        //Act
        List result = crawlerController.GetAllMoviesJson();

        //Assert
        Assert.assertTrue(result.get(0).toString().contains(expectedJson));
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
        String expectedJson = "{\"id\":1,\"title\":\"FlyWithMe\",\"category\":\"Books\",\"genre\":\"Tech\",\"format\":\"Ebook\",\"author\":[\"Robert C. Martin\"],\"publisher\":\"Prentice Hall\",\"isbn\":\"978-013235-0884\",\"year\":2008}";
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
        String expectedJson = "{\"id\":1,\"title\":\"The old one\",\"category\":\"Music\",\"genre\":\"Classical\",\"format\":\"CD\",\"artist\":\"Ludwig van Beethoven\",\"year\":2012}";
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
        String expectedJson = "{\"id\":1,\"title\":\"Forest\",\"category\":\"Forest Gump\",\"genre\":\"Drama\",\"format\":\"DVD\",\"director\":\"Robert Zemeckis\",\"writer\":[\"Winston Groom\",\"Eric Roth\"],\"star\":[\"Tom Hanks\",\"Rebecca Williams\",\"Sally Field\",\"Michael Conner Humphreys\"],\"year\":1994}";
        //Act
        String s = crawlerController.ConvertMovieObjectToJson(movie);
        //Assert
        Assert.assertEquals(expectedJson,s);
    }
}