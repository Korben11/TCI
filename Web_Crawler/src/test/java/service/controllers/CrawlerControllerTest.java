package service.controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import service.extractors.DataExtractor;
import service.models.Book;

import java.util.Arrays;

public class CrawlerControllerTest {
    CrawlerController crawlerController = new CrawlerController();

    @Test
    public void GetAllBooksShouldReturnListOfAllBook(){
        //Arrange
        Book book = new Book(1L,
                "Books",
                "Tech",
                "Ebook",
                Arrays.asList("Robert C. Martin"),
                "Prentice Hall",
                "978-013235-0884",
                2008);
        //Act
        //Assert
    }

    @Test
    public void ConvertBookObjectToJsonShouldReturnAStringWithJsonFormat() throws JsonProcessingException {
        //Arrange
        Book book = new Book(1L,
                "Books",
                "Tech",
                "Ebook",
                Arrays.asList("Robert C. Martin"),
                "Prentice Hall",
                "978-013235-0884",
                2008);
        //Act
        String s = crawlerController.ConvertBookObjectToJson(book);
        //Assert
    }

}