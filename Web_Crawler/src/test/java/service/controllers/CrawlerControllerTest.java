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
                "FlyWithMe",
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
                "FlyWithMe",
                "Books",
                "Tech",
                "Ebook",
                Arrays.asList("Robert C. Martin"),
                "Prentice Hall",
                "978-013235-0884",
                2008);
        String expectedJson = "{\n" +
                "  \"id\" : 1,\n" +
                "  \"title\" : \"FlyWithMe\",\n" +
                "  \"category\" : \"Books\",\n" +
                "  \"genre\" : \"Tech\",\n" +
                "  \"format\" : \"Ebook\",\n" +
                "  \"author\" : [ \"Robert C. Martin\" ],\n" +
                "  \"publisher\" : \"Prentice Hall\",\n" +
                "  \"isbn\" : \"978-013235-0884\",\n" +
                "  \"year\" : 2008\n" +
                "}";
        //Act
        String s = crawlerController.ConvertBookObjectToJson(book);
        //Assert
        Assert.assertEquals(expectedJson,s);
    }

}