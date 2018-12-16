package service.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private static final String path = "/books";
    private static final String pathDetail = path + "/{id}";


    public List<String> getAllBookTitles() {
        return null;
    }

    public String getABook(String a_design_patterns) {
        return null;
    }

    public List<String> getAllBooks() {
        return null;
    }
}
