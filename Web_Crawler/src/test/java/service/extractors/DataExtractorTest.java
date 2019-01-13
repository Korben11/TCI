package service.extractors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import service.CrawlerExecuter;
import service.models.Book;
import service.models.Movie;
import service.models.Music;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DataExtractorTest {

    private DataExtractor dataExtractor;
    private Document document;

    /**
     * This test basically check if Book object is correctly retrieved from the Document file received from the crawler and scraper
     * It use a mock Document (indirect input) that contains object of Element type which provides data of Book object in HTML format
     * The method GenerateBookFromDocument with parse those data into a Book object and return it (direct output)
     * The test should verify if the identification of the expected object resembles the object retrieved from the tested method
     */
    @Test
    public void GenerateBookFromDocumentMustReturnBookObject(){

        //Arrange
        document = mock(Document.class);
        dataExtractor = new DataExtractor();
        Element element = new Element("test");
        Elements elements = new Elements();
        elements.add(element);
        Book book = new Book(1L,
                "",
                "Books",
                "Tech",
                "Ebook",
                Arrays.asList("Robert C. Martin"),
                "Prentice Hall",
                "978-013235-0884",
                2008);

        when(document.select("div.media-details")).thenReturn(elements);

        //Act
        Book returnedBook = (Book) dataExtractor.GenerateBookFromDocument(document);


        //Assert
        assertEquals(book, returnedBook);
    }


    /**
     * This test basically check if Music object is correctly retrieved from the Document file received from the crawler and scraper
     * It use a mock Document (indirect input) that contains object of Element type which provides data of Music object in HTML format
     * The method GenerateMusicFromDocument with parse those data into a Music object and return it (direct output)
     * The test should verify if the identification of the expected object resembles the object retrieved from the tested method
     */
    @Test
    public void GenerateMusicFromDocumentMustReturnMusicObject(){
        //Arrange
        document = mock(Document.class);
        dataExtractor = new DataExtractor();
        Element element = new Element("test");
        Elements elements = new Elements();
        elements.add(element);
        Music music = new Music(1L,
                "",
                "Music",
                "Classical",
                "CD",
                "Ludwig van Beethoven",
                2012);

        when(document.select("div.media-details")).thenReturn(elements);

        //Act
        Music returnedMusic = (Music) dataExtractor.GenerateMusicFromDocument(document);

        //Assert
        assertEquals(music, returnedMusic);
    }


    /**
     * This test basically check if Movie object is correctly retrieved from the Document file received from the crawler and scraper
     * It use a mock Document (indirect input) that contains object of Element type which provides data of Movie object in HTML format
     * The method GenerateMovieFromDocument with parse those data into a Movie object and return it (direct output)
     * The test should verify if the identification of the expected object resembles the object retrieved from the tested method
     */
    @Test
    public void GenerateMovieFromDocumentMustReturnMovieObject(){
        //Arrange
        document = mock(Document.class);
        dataExtractor = new DataExtractor();
        Element element = new Element("test");
        Elements elements = new Elements();
        elements.add(element);
        Movie movie = new Movie(1L,
                "Forest",
                "Forest Gump",
                "Drama",
                "DVD",
                "Robert Zemeckis",
                Arrays.asList("Winston Groom", "Eric Roth"),
                Arrays.asList("Tom Hanks", "Rebecca Williams", "Sally Field", "Michael Conner Humphreys"),
                1994);

        when(document.select("div.media-details")).thenReturn(elements);

        //Act
        Movie returnedMovie = (Movie) dataExtractor.GenerateMovieFromDocument(document);

        //Assert
        assertEquals(movie, returnedMovie);
    }

}