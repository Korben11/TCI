package service.controllers;

import org.springframework.web.bind.annotation.*;
import service.models.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * This class returns books according to what user wants
 */
@RestController
public class BookController {
    private static final String path = "/books";
    private static final String pathDetail = path + "/{id}";

    public BookController(){
    }

    public List<String> getAllBooks() {
        return null;
    }

    public List<String> getAllBookTitles() {
        return null;
    }

    public String getABookWithTitle(String title) {
        return null;
    }

    public String getABookWithMatchedId(String id) {
        return null;
    }

    public String getABookWithMatchedYear(String year) {
        return null;
    }

}
