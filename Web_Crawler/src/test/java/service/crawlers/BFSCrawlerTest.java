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

    /**
     *
     * Create invalid crawler
     *
     * @throws MalformedURLException
     */
    @Test(expected = MalformedURLException.class)
    public void constructorBFSCrawler() throws MalformedURLException {
        // arrange
        String wrongUrl = "test.url";

        // act
        BFSCrawler crawler = new BFSCrawler(wrongUrl);
    }

    /**
     *
     * Create valid crawler
     *
     * @throws MalformedURLException
     * @result Crawler with correct url was created
     */
    @Test()
    public void constructorBFSCrawlerValid() throws MalformedURLException {
        // arrange

        // act
        BFSCrawler crawler = new BFSCrawler(url);

        // assert
        assertTrue(crawler.getStartingUrl().toString().equals(this.url));
    }

    /**
     *
     * try to get next page, expected to return jsoup document
     *
     * @throws MalformedURLException
     * @throws NextPageDoesNotExistsException
     * @result next page should be returned as instance of jsoup document
     */
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

    /**
     *
     * test if crawler has next page to crawl
     *
     * @throws MalformedURLException
     * @result true, crawler should have page to continue with
     */
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

    /**
     *
     * test if crawler has next page to crawl
     *
     * @throws MalformedURLException
     * @result false, crawler should not have page to continue with
     */
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

    /**
     *
     * test if domain is matching website being crawled
     *
     * @throws MalformedURLException
     * @result true, domain should match
     */
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

    /**
     *
     * test if domain is matching website being crawled
     * in this case domain should not match
     *
     * @throws MalformedURLException
     * @result false, domain should not match
     */
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