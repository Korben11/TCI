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
     * check if method getAllBookTitles() returns a correct list of all books' found
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
     * check if method getBookWithName() returns a matched book's name with all details
     */
    @ParameterizedTest(name = "{index} => a={0}")
    @CsvSource({
            "A Design Patterns",
            "Clean Code",
            "Refactoring"
    })
    public void returnABookWithMatchedName(String a){
        //Arrange
        BookController book = mock(BookController.class);
        //Act
        //Assert
        assertEquals(bookTitleList.get(0),book.getABook(a));
    }
}