package service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private static final String path = "/books";
    private static final String pathDetail = path + "/{id}";

//    @GetMapping(path)
//    public List getBooks(){
//        retrieve book list from jsoup
//    }
}
