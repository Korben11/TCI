package service.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    private static final String path = "/books";
    private static final String pathDetail = path + "/{id}";

//    @GetMapping(path)
//    public List getBooks(){
//        //retrieve book list from jsoup
//    }
}
