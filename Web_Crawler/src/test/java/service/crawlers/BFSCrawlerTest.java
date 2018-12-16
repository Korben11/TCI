package service.crawlers;

import org.jsoup.nodes.Document;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class BFSCrawlerTest {

    private String url = "http://test.url";

    @Before
    public void setUp() throws Exception {

    }

    @Test(expected = MalformedURLException.class)
    public void constructorBFSCrawler() throws MalformedURLException {
        // arrange
        String wrongUrl = "test.url";

        // act
        BFSCrawler crawler = new BFSCrawler(wrongUrl);
    }

    @Test
    public void next() throws MalformedURLException, NextPageDoesNotExistsException {
        // arrange
        String url = "http://test.url";
        BFSCrawler crawler = new BFSCrawler(url);
        LinkedList<String> pages = new LinkedList<>();
        pages.add("http://i296827.hera.fhict.nl/catalog.php");
        crawler.addPagesToVisit(pages);

        // act
        Document doc = crawler.next();

        // assert

    }

    @Test
    public void hasNext() throws MalformedURLException {
        // arrange
        LinkedList<String> pages = new LinkedList<>();
        pages.add("http://i296827.hera.fhict.nl/catalog.php");
        pages.add("http://i296827.hera.fhict.nl/catalog.php?cat=books");
        pages.add("http://i296827.hera.fhict.nl/catalog.php?cat=movies");
        pages.add("http://i296827.hera.fhict.nl/catalog.php?cat=music");
        BFSCrawler crawler = new BFSCrawler(url);
        crawler.addPagesToVisit(pages);

        // act
        boolean hasNext = crawler.hasNext();

        // assert
        assertTrue(hasNext);
    }

    @Test
    public void doesntHaveNext() throws MalformedURLException {
        // arrange
        LinkedList<String> pages = new LinkedList<>();
        BFSCrawler crawler = new BFSCrawler(url);
        crawler.addPagesToVisit(pages);

        // act
        boolean hasNext = crawler.hasNext();

        // assert
        assertFalse(hasNext);
    }

    @Test
    public void matchingDomain() throws MalformedURLException {
        // arrange
        LinkedList<String> pages = new LinkedList<>();
        pages.add("http://i296827.hera.fhict.nl/catalog.php");
        BFSCrawler crawler = new BFSCrawler(url);
        crawler.addPagesToVisit(pages);

        // act
        boolean mathingDomain = crawler.hasNext();

        // assert
        assertTrue(mathingDomain);
    }

    @Test
    public void domainDoesNotMatch() throws MalformedURLException {
        // arrange
        LinkedList<String> pages = new LinkedList<>();
        String page = "http://fail.com/catalog.php";
        BFSCrawler crawler = new BFSCrawler(url);
        crawler.addPagesToVisit(pages);

        // act
        boolean matchingDomain = crawler.matchingDomain(page);

        // assert
        assertFalse(matchingDomain);
    }

}