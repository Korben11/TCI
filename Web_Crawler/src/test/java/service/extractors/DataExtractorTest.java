package service.extractors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import service.models.Book;
import service.models.Movie;
import service.models.Music;
import java.util.List;

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

        String title = "The Clean Coder: A Code of Conduct for Professional Programmers";
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

        when(document.select("tbody")).thenReturn(elements);
        when(document.title()).thenReturn(title);

        //Act
        List<Book> returnedBook = dataExtractor.GenerateBookFromDocument(document);

        //Assert
        assertEquals(1, returnedBook.size());
        assertTrue(returnedBook.get(0).getTitle() == title);
        assertEquals("007-6092046981", returnedBook.get(0).getIsbn());
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

        String title = "Elvis Forever";
        Element element = new Element("tbody");
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Category"))
                .appendChild(new Element("td").append("Music")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Genre"))
                .appendChild(new Element("td").append("Rock")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Format"))
                .appendChild(new Element("td").append("Vinyl")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Year"))
                .appendChild(new Element("td").append("2015")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Artist"))
                .appendChild(new Element("td").append("Elvis Presley")));
        Elements elements = new Elements();
        elements.add(element);

        when(document.select("tbody")).thenReturn(elements);
        when(document.title()).thenReturn(title);

        //Act
        List<Music> returnedMusic = dataExtractor.GenerateMusicFromDocument(document);

        //Assert
        assertEquals(1, returnedMusic.size());
        assertTrue(returnedMusic.get(0).getTitle() == title);
        assertEquals("Elvis Presley", returnedMusic.get(0).getArtist());
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

        String title = "Forrest Gump";
        Element element = new Element("tbody");
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Category"))
                .appendChild(new Element("td").append("Movies")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Genre"))
                .appendChild(new Element("td").append("Drama")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Format"))
                .appendChild(new Element("td").append("DVD")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Year"))
                .appendChild(new Element("td").append("1994")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Director"))
                .appendChild(new Element("td").append("Robert Zemeckis")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Writers"))
                .appendChild(new Element("td").append("Winston Groom, Eric Roth")));
        element.appendChild(new Element("tr")
                .appendChild(new Element("th").append("Stars"))
                .appendChild(new Element("td").append("Tom Hanks, Rebecca Williams, Sally Field, Michael Conner Humphreys")));
        Elements elements = new Elements();
        elements.add(element);

        when(document.select("tbody")).thenReturn(elements);
        when(document.title()).thenReturn(title);

        //Act
        List<Movie> returnedMovie = dataExtractor.GenerateMovieFromDocument(document);

        //Assert
        assertEquals(1, returnedMovie.size());
        assertTrue(returnedMovie.get(0).getTitle() == title);
        assertEquals(2, returnedMovie.get(0).getWriter().size());
        assertTrue(returnedMovie.get(0).getWriter().contains("Winston Groom") && returnedMovie.get(0).getWriter().contains("Eric Roth"));
    }

}