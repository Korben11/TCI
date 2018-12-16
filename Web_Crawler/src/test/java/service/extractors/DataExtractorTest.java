package service.extractors;

import org.jsoup.nodes.Document;
import org.junit.Test;
import service.models.Book;
import service.models.Movie;
import service.models.Music;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DataExtractorTest {

    private DataExtractor dataExtractor;
    private Document document;

    @Test
    public void GenerateBookFromDocumentMustReturnBookObject(){
        //Arrange
        dataExtractor = mock(DataExtractor.class);
        document = mock(Document.class);

        //Act
        dataExtractor.GenerateBookFromDocument(document);

        //Assert
        assertEquals(dataExtractor.getBookList().get(dataExtractor.getBookList().size() - 1),
                new Book(1L,
                        "Books",
                        "Tech",
                        "Ebook",
                         Arrays.asList("Robert C. Martin"),
                        "Prentice Hall",
                        "978-013235-0884",
                        2008));
        assertTrue(dataExtractor.getBookList().size() > 0);
    }

    @Test
    public void GenerateMusicFromDocumentMustReturnMusicObject(){
        //Arrange
        dataExtractor = mock(DataExtractor.class);
        document = mock(Document.class);

        //Act
        dataExtractor.GenerateMusicFromDocument(document);

        //Assert
        assertEquals(dataExtractor.getMusicList().get(dataExtractor.getMusicList().size() - 1),
                new Music(1L,
                        "Music",
                        "Classical",
                        "CD",
                        "Ludwig van Beethoven",
                        2012));
        assertTrue(dataExtractor.getMusicList().size() > 0);
    }

    @Test
    public void GenerateMovieFromDocumentMustReturnMovieObject(){
        //Arrange
        dataExtractor = mock(DataExtractor.class);
        document = mock(Document.class);

        //Act
        dataExtractor.GenerateMovieFromDocument(document);

        //Assert
        assertEquals(dataExtractor.getMovieList().get(dataExtractor.getMovieList().size() - 1),
                new Movie(1L,
                        "Forest Gump",
                        "Drama",
                        "DVD",
                        "Robert Zemeckis",
                        Arrays.asList("Winston Groom", "Eric Roth"),
                        Arrays.asList("Tom Hanks", "Rebecca Williams", "Sally Field", "Michael Conner Humphreys"),
                        1994));
        assertTrue(dataExtractor.getMovieList().size() > 0);
    }

    @Test
    public void VerifyGenerateBookFromDocumentIsCalled(){
        //Arrange
        dataExtractor = mock(DataExtractor.class);
        document = mock(Document.class);

        //Act
        dataExtractor.GenerateBookFromDocument(document);

        //Assert
        verify(dataExtractor).GenerateBookFromDocument(document);
    }

    @Test
    public void VerifyGenerateMusicFromDocumentIsCalled(){
        //Arrange
        dataExtractor = mock(DataExtractor.class);
        document = mock(Document.class);

        //Act
        dataExtractor.GenerateMusicFromDocument(document);

        //Assert
        verify(dataExtractor).GenerateMusicFromDocument(document);
    }

    @Test
    public void VerifyGenerateMovieFromDocumentIsCalled(){
        //Arrange
        dataExtractor = mock(DataExtractor.class);
        document = mock(Document.class);

        //Act
        dataExtractor.GenerateMovieFromDocument(document);

        //Assert
        verify(dataExtractor).GenerateMovieFromDocument(document);
    }
}