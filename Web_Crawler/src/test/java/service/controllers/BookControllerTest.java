package service.controllers;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;


public class BookControllerTest {
    List<String> bookTitleList = new ArrayList<String>();
    List<String> fullBookList = new ArrayList<String>();

    @Before
    public void setUpListBook(){
        fullBookList.add("\"name\":\"A Design Patterns: Elements of Reusable Object-Oriented Software\",\n" +
                " \"genre\":\"Tech\",\n" +
                " \"format\":\"Paperback\",\n" +
                " \"year\":1994,\n" +
                " \"authors\":\"Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides\",\n" +
                " \"publisher\":\"Prentice Hall\",\n" +
                " \"ISBN\":\"978-0201633610\"");
        fullBookList.add("\"name\":\"Clean Code: A Handbook of Agile Software Craftsmanship\",\n" +
                " \"genre\":\"Tech\",\n" +
                " \"format\":\"Ebook\",\n" +
                " \"year\":2008,\n" +
                " \"authors\":\"Robert C.Martin\",\n" +
                " \"publisher\":\"Prentice Hall\",\n" +
                " \"ISBN\":\"978-0132350884\"");
        fullBookList.add("\"name\":\"Refactoring: Improving the Design of Existing Code\",\n" +
                " \"genre\":\"Tech\",\n" +
                " \"format\":\"Hardcover\",\n" +
                " \"year\":1999,\n" +
                " \"authors\":\"Martin Fowler, Kent Beck, John Brant, William Opdyke, Don Roberts\",\n" +
                " \"publisher\":\"\tAddison-Wesley Professional\",\n" +
                " \"ISBN\":\"978-0201485677\"");
        fullBookList.add("\"name\":\"The Clean Coder: A Code of Conduct for Professional Programmers\",\n" +
                " \"genre\":\"Tech\",\n" +
                " \"format\":\"Audio\",\n" +
                " \"year\":2011,\n" +
                " \"authors\":\"Robert C.Martin\",\n" +
                " \"publisher\":\"Prentice Hall\",\n" +
                " \"ISBN\":\"007-6092046981\"");
        bookTitleList.add("A Design Patterns: Elements of Reusable Object-Oriented Software");
        bookTitleList.add("Clean Code: A Handbook of Agile Software Craftsmanship");
        bookTitleList.add("Refactoring: Improving the Design of Existing Code");
        bookTitleList.add("The Clean Coder: A Code of Conduct for Professional Programmers");
    }

    @After
    public void clearListBook(){
        bookTitleList.clear();
        fullBookList.clear();
    }
    /**
     * check if method getAllBook() returns a correct list of all books' found
     */
    @Test
    public void returnAllRetrievedBook(){
        //Arrange
        BookController book = mock(BookController.class);
        //Act
        when(book.getAllBooks()).thenReturn(fullBookList);
        //Assert
        assertEquals(fullBookList, book.getAllBooks());
    }

    /**
     * very if method getAllBook() was called
     */
    @Test
    public void verifyAllRetrievedBookMethodIsCalled(){
        //Arrange
        BookController book = mock(BookController.class);
        //Act
        when(book.getAllBooks()).thenReturn(fullBookList);
        //Assert
        verify(book).getAllBooks();
    }

    /**
     * check if method getAllBookTitles() returns a correct list of all books' titles found
     */
    @Test
    public void returnAllRetrievedBookTitles(){
        //Arrange
        BookController book = mock(BookController.class);
        //Act
        when(book.getAllBookTitles()).thenReturn(bookTitleList);
        //Assert
        assertEquals(bookTitleList, book.getAllBookTitles());
    }

    /**
     * check if method getAllBookTitles() returns a correct list of all books' titles found
     */
    @Test
    public void verifyAllRetrievedBookTitles(){
        //Arrange
        BookController book = mock(BookController.class);
        //Act
        when(book.getAllBookTitles()).thenReturn(bookTitleList);
        //Assert
        verify(book).getAllBookTitles();
    }

    /**
     * check if method getBookWithName() returns a matched book's name with all details
     */
    @ParameterizedTest(name = "{index} => a={0}, b={1}")
    @CsvSource({
            "A Design Patterns,0",
            "Clean Code,1",
            "Refactoring,2"
    })
    public void returnABookWithMatchedName(String a, int b){
        //Arrange
        BookController book = mock(BookController.class);
        //Act
        //Assert
        assertEquals(bookTitleList.get(b),book.getABookWithTitle(a));
    }

    /**
     * check if method getBookWithName() returns a matched book's id with all details
     */
    @ParameterizedTest(name = "{index} => a={0}, b={1}")
    @CsvSource({
            "102,1",
            "104,3",
            "101,0"
    })
    public void returnABookWithMatchedId(String a, int b){
        //Arrange
        BookController book = mock(BookController.class);
        //Act
        //Assert
        assertEquals(bookTitleList.get(b),book.getABookWithMatchedId(a));
    }

    /**
     * check if method getBookWithName() returns a matched book's year with all details
     */
    @ParameterizedTest(name = "{index} => a={0}")
    @CsvSource({
            "2008,1",
            "1999,2",
            "2011,3"
    })
    public void returnABookWithMatchedYear(String a, int b){
        //Arrange
        BookController book = mock(BookController.class);
        //Act
        //Assert
        assertEquals(bookTitleList.get(b),book.getABookWithMatchedYear(a));
    }
}