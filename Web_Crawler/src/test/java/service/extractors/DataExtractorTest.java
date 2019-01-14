package service.extractors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
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
     * This test checks if Book object is correctly retrieved from the Document file received from the crawler and scraper
     * It use a mocked Document that contains objects of Element type which provides data of Book object in HTML format
     * The method GenerateBookFromDocument with parse those data into Book object and return it
     * The test basically determines if the identification of the expected object resembles the object declared in the test method
     * Test type: direct output (object Book must be generated)
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
        Book returnedBook = dataExtractor.GenerateBookFromDocument(document);

        //Assert
        assertTrue(returnedBook.getTitle() == title);
        assertEquals("007-6092046981", returnedBook.getIsbn());
    }


    /**
     * This test checks if Music object is correctly retrieved from the Document file received from the crawler and scraper
     * It use a mocked Document that contains objects of Element type which provides data of Music object in HTML format
     * The method GenerateMusicFromDocument with parse those data into Music object and return it
     * The test basically determines if the identification of the expected object resembles the object declared in the test method
     * Test type: direct output (object Music must be generated)
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
        Music returnedMusic = dataExtractor.GenerateMusicFromDocument(document);

        //Assert
        assertTrue(returnedMusic.getTitle() == title);
        assertEquals("Elvis Presley", returnedMusic.getArtist());
    }


    /**
     * This test checks if Movie object is correctly retrieved from the Document file received from the crawler and scraper
     * It use a mocked Document that contains objects of Element type which provides data of Movie object in HTML format
     * The method GenerateMovieFromDocument with parse those data into Movie object and return it
     * The test basically determines if the identification of the expected object resembles the object declared in the test method
     * Test type: direct output (object Movie must be generated)
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
        Movie returnedMovie = dataExtractor.GenerateMovieFromDocument(document);

        //Assert
        assertTrue(returnedMovie.getTitle() == title);
        assertEquals(2, returnedMovie.getWriter().size());
        assertTrue(returnedMovie.getWriter().contains("Winston Groom") && returnedMovie.getWriter().contains("Eric Roth"));
    }


    /**
     * This test checks if the movie title can be retrieved from the Document file received from the crawler and scraper
     * It use mocked Document, Node and Element that help checking if the HTML data retrieved are that of Movie object
     * The test should verify if the title() method is called during the data extracting process
     * Test type: indirect input (title of the document must be processed)
     */
    @Test
    public void GenerateMovieFromDocumentShouldGetDocumentTitle(){

        //Arrange
        document = mock(Document.class);
        Node node = mock(Node.class);
        Element element = mock(Element.class);
        Elements elements = new Elements();
        elements.add(element);
        dataExtractor = new DataExtractor();

        when(document.select("tbody")).thenReturn(elements);
        when(element.childNode(0)).thenReturn(node);
        when(node.toString()).thenReturn("Movies");

        //Act
        dataExtractor.GenerateMovieFromDocument(document);

        //Assert
        verify(document).title();
    }


    /**
     * This test checks if the music title can be retrieved from the Document file received from the crawler and scraper
     * It use mocked Document, Node and Element that help checking if the HTML data retrieved are that of Music object
     * The test should verify if the title() method is called during the data extracting process
     * Test type: indirect input (title of the document must be processed)
     */
    @Test
    public void GenerateMusicFromDocumentShouldGetDocumentTitle(){

        //Arrange
        document = mock(Document.class);
        Node node = mock(Node.class);
        Element element = mock(Element.class);
        Elements elements = new Elements();
        elements.add(element);
        dataExtractor = new DataExtractor();

        when(document.select("tbody")).thenReturn(elements);
        when(element.childNode(0)).thenReturn(node);
        when(node.toString()).thenReturn("Music");

        //Act
        dataExtractor.GenerateMusicFromDocument(document);

        //Assert
        verify(document).title();
    }


    /**
     * This test checks if the book title can be retrieved from the Document file received from the crawler and scraper
     * It use mocked Document, Node and Element that help checking if the HTML data retrieved are that of Book object
     * The test should verify if the title() method is called during the data extracting process
     * Test type: indirect input (title of the document must be processed)
     */
    @Test
    public void GenerateBookFromDocumentShouldGetDocumentTitle(){

        //Arrange
        document = mock(Document.class);
        Node node = mock(Node.class);
        Element element = mock(Element.class);
        Elements elements = new Elements();
        elements.add(element);
        dataExtractor = new DataExtractor();

        when(document.select("tbody")).thenReturn(elements);
        when(element.childNode(0)).thenReturn(node);
        when(node.toString()).thenReturn("Books");

        //Act
        dataExtractor.GenerateBookFromDocument(document);

        //Assert
        verify(document).title();
    }
}