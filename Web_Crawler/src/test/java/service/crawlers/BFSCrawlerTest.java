package service.crawlers;

import mockit.Mocked;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.jsoup.helper.HttpConnection;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class BFSCrawlerTest {
//    private static final String indexHtml = "<html><head><title>Personal Media Library</title><link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\"></head><body><div class=\"header\"><div class=\"wrapper\"><h1 class=\"branding-title\"><a href=\"catalog.php\">Personal Media Library</a></h1><ul class=\"nav\"> <li class=\"books \"><a href=\"catalog.php?cat=books\">Books</a></li> <li class=\"movies \"><a href=\"catalog.php?cat=movies\">Movies</a></li> <li class=\"music \"><a href=\"catalog.php?cat=music\">Music</a></li> <li class=\"suggest on\"><a href=\"suggest.php\">Suggest</a></li> </ul></div></div><div id=\"content\"><div class=\"section catalog random\"><div class=\"wrapper\"><h2>May we suggest something?</h2><ul class=\"items\"><li><a href='details.php?id=103'><img src='img/media/refactoring.jpg ' alt='Refactoring: Improving the Design of Existing Code' /><p>View Details </p></a></li><li><a href='details.php?id=104'><img src='img/media/clean_coder.jpg ' alt='The Clean Coder: A Code of Conduct for Professional Programmers' /><p>View Details </p></a></li><li><a href='details.php?id=201'><img src='img/media/forest_gump.jpg ' alt='Forrest Gump' /><p>View Details </p></a></li><li><a href='details.php?id=304'><img src='img/media/nat_king_cole.jpg ' alt='The Very Thought of You' /><p>View Details </p></a></li></ul></div></div> </div> <!-- end content--> <div class=\"footer\"> <div class=\"wrapper\"> <ul> <li><a href=\"http://twitter.com/treehouse\">Twitter</a></li> <li><a href=\"https://www.facebook.com/TeamTreehouse\">Facebook</a></li> </ul> <p>&copy;2019 Personal Media Library</p> </div> </div> <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js\"></script> <script type=\"text/javascript\" src=\"js/script.js\"></script> </body></html>";
    private static final String indexHtml = "<html><head><title>Personal Media Library</title><link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\"></head><body><div class=\"header\"><div class=\"wrapper\"><h1 class=\"branding-title\"><a href=\"catalog.php\">Personal Media Library</a></h1><ul class=\"nav\"> <li class=\"books \"><a href=\"catalog.php?cat=books\">Books</a></li> <li class=\"movies \"><a href=\"catalog.php?cat=movies\">Movies</a></li> <li class=\"music \"><a href=\"catalog.php?cat=music\">Music</a></li> <li class=\"suggest on\"><a href=\"suggest.php\">Suggest</a></li> </ul></div></div><div id=\"content\"><div class=\"section catalog random\"><div class=\"wrapper\"><h2>May we suggest something?</h2><ul class=\"items\"><li><a href='details.php?id=101'><img src='img/media/design_patterns.jpg ' alt='A Design Patterns: Elements of Reusable Object-Oriented Software' /><p>View Details </p></a></li><li><a href='details.php?id=201'><img src='img/media/forest_gump.jpg ' alt='Forrest Gump' /><p>View Details </p></a></li><li><a href='details.php?id=204'><img src='img/media/princess_bride.jpg ' alt='The Princess Bride' /><p>View Details </p></a></li><li><a href='details.php?id=303'><img src='img/media/garth_brooks.jpg ' alt='No Fences' /><p>View Details </p></a></li></ul></div></div> </div> <!-- end content--> <div class=\"footer\"> <div class=\"wrapper\"> <ul> <li><a href=\"http://twitter.com/treehouse\">Twitter</a></li> <li><a href=\"https://www.facebook.com/TeamTreehouse\">Facebook</a></li> </ul> <p>&copy;2019 Personal Media Library</p> </div> </div> <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js\"></script> <script type=\"text/javascript\" src=\"js/script.js\"></script> </body></html>";
    private String url = "http://test.url";

    @Test()
    public void testExtractLinks() throws MalformedURLException, IOException {
        // arrange
        // create mock
        HttpConnection connection = mock(HttpConnection.class);
        BFSCrawler crawler = new BFSCrawler(url, connection);
        LinkedList<String> expectedLinks = new LinkedList<String>();
        expectedLinks.add(String.format("%s/catalog.php", url));
        expectedLinks.add(String.format("%s/catalog.php?cat=books", url));
        expectedLinks.add(String.format("%s/catalog.php?cat=movies", url));
        expectedLinks.add(String.format("%s/catalog.php?cat=music", url));
        expectedLinks.add(String.format("%s/suggest.php", url));
        expectedLinks.add(String.format("%s/details.php?id=101", url));
        expectedLinks.add(String.format("%s/details.php?id=201", url));
        expectedLinks.add(String.format("%s/details.php?id=204", url));
        expectedLinks.add(String.format("%s/details.php?id=303", url));

        Document document = Jsoup.parse(indexHtml, url);

        // define return value for method getUniqueId()
        when(connection.url(url)).thenReturn(connection);
        when(connection.get()).thenReturn(document);

        // act
        LinkedList<String> urls = crawler.extractLinks(crawler.getStartingUrl());

        // assert
        assertThat(urls, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedLinks.toArray()));
    }

    /**
     *
     * Create invalid crawler
     *
     * @type direct
     * @throws MalformedURLException
     */
    @Test(expected = MalformedURLException.class)
    public void constructorBFSCrawler() throws MalformedURLException {
        // arrange
        String wrongUrl = "test.url";
        HttpConnection connection = mock(HttpConnection.class);

        // act
        BFSCrawler crawler = new BFSCrawler(wrongUrl, connection);
    }

    /**
     *
     * Create valid crawler
     *
     * @type direct
     * @throws MalformedURLException
     * @result Crawler with correct url was created
     */
    @Test()
    public void constructorBFSCrawlerValid() throws MalformedURLException {
        // arrange
        HttpConnection connection = mock(HttpConnection.class);

        // act
        BFSCrawler crawler = new BFSCrawler(url, connection);

        // assert
        assertTrue(crawler.getStartingUrl().toString().equals(this.url));
    }

    /**
     *
     * try to get next page, expected to return jsoup document
     *
     * @type indirect, mock jsoup
     * @throws MalformedURLException
     * @throws NextPageDoesNotExistsException
     * @result next page should be returned as instance of jsoup document
     */
    @Test
    public void next() throws MalformedURLException, NextPageDoesNotExistsException {
        // arrange
        String url = "http://test.url";
        HttpConnection connection = mock(HttpConnection.class);
        BFSCrawler crawler = new BFSCrawler(url, connection);
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
     * @type direct
     * @throws MalformedURLException
     * @result true, crawler should have page to continue with
     */
    @Test
    public void hasNext() throws MalformedURLException {
        // arrange
        HttpConnection connection = mock(HttpConnection.class);
        LinkedList<String> pages = new LinkedList<>();
        pages.add("http://i296827.hera.fhict.nl/catalog.php");
        pages.add("http://i296827.hera.fhict.nl/catalog.php?cat=books");
        pages.add("http://i296827.hera.fhict.nl/catalog.php?cat=movies");
        pages.add("http://i296827.hera.fhict.nl/catalog.php?cat=music");
        BFSCrawler crawler = new BFSCrawler(url, connection);
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
     * @type direct
     * @throws MalformedURLException
     * @result false, crawler should not have page to continue with
     */
    @Test
    public void doesntHaveNext() throws MalformedURLException {
        // arrange
        HttpConnection connection = mock(HttpConnection.class);
        LinkedList<String> pages = new LinkedList<>();
        BFSCrawler crawler = new BFSCrawler(url, connection);
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
     * @type direct
     * @throws MalformedURLException
     * @result true, domain should match
     */
    @Test
    public void matchingDomain() throws MalformedURLException {
        // arrange
        HttpConnection connection = mock(HttpConnection.class);
        LinkedList<String> pages = new LinkedList<>();
        pages.add("http://i296827.hera.fhict.nl/catalog.php");
        BFSCrawler crawler = new BFSCrawler(url, connection);
        crawler.addPagesToVisit(pages);

        // act
        boolean matchingDomain = crawler.hasNext();

        // assert
        assertTrue(matchingDomain);
    }

    /**
     *
     * test if domain is matching website being crawled
     * in this case domain should not match
     *
     * @type direct
     * @throws MalformedURLException
     * @result false, domain should not match
     */
    @Test
    public void domainDoesNotMatch() throws MalformedURLException {
        // arrange
        HttpConnection connection = mock(HttpConnection.class);
        LinkedList<String> pages = new LinkedList<>();
        String page = "http://fail.com/catalog.php";
        BFSCrawler crawler = new BFSCrawler(url, connection);
        crawler.addPagesToVisit(pages);

        // act
        boolean matchingDomain = crawler.matchingDomain(page);

        // assert
        assertFalse(matchingDomain);
    }

}