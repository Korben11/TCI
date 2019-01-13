package service.crawlers;

import com.jcraft.jsch.IO;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.jsoup.helper.HttpConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class BFSCrawlerTest {
    private static final String indexHtml = "<html><head><title>Personal Media Library</title><link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\"></head><body><div class=\"header\"><div class=\"wrapper\"><h1 class=\"branding-title\"><a href=\"catalog.php\">Personal Media Library</a></h1><ul class=\"nav\"> <li class=\"books \"><a href=\"catalog.php?cat=books\">Books</a></li> <li class=\"movies \"><a href=\"catalog.php?cat=movies\">Movies</a></li> <li class=\"music \"><a href=\"catalog.php?cat=music\">Music</a></li> <li class=\"suggest on\"><a href=\"suggest.php\">Suggest</a></li> </ul></div></div><div id=\"content\"><div class=\"section catalog random\"><div class=\"wrapper\"><h2>May we suggest something?</h2><ul class=\"items\"><li><a href='details.php?id=101'><img src='img/media/design_patterns.jpg ' alt='A Design Patterns: Elements of Reusable Object-Oriented Software' /><p>View Details </p></a></li><li><a href='details.php?id=201'><img src='img/media/forest_gump.jpg ' alt='Forrest Gump' /><p>View Details </p></a></li><li><a href='details.php?id=204'><img src='img/media/princess_bride.jpg ' alt='The Princess Bride' /><p>View Details </p></a></li><li><a href='details.php?id=303'><img src='img/media/garth_brooks.jpg ' alt='No Fences' /><p>View Details </p></a></li></ul></div></div> </div> <!-- end content--> <div class=\"footer\"> <div class=\"wrapper\"> <ul> <li><a href=\"http://twitter.com/treehouse\">Twitter</a></li> <li><a href=\"https://www.facebook.com/TeamTreehouse\">Facebook</a></li> </ul> <p>&copy;2019 Personal Media Library</p> </div> </div> <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js\"></script> <script type=\"text/javascript\" src=\"js/script.js\"></script> </body></html>";
    private String url = "http://test.url";

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

    @Test()
    public void getStartingUrl() throws MalformedURLException {
        // arrange
        HttpConnection connection = mock(HttpConnection.class);
        BFSCrawler crawler = new BFSCrawler(url, connection);

        // act
        String startUrl = crawler.getStartingUrl().toString();

        // assert
        assertEquals(url, startUrl);
    }

    @Test()
    public void getMaxDepth() throws MalformedURLException {
        // arrange
        HttpConnection connection = mock(HttpConnection.class);
        BFSCrawler crawler = new BFSCrawler(url, connection);

        // act
        int maxDepth = crawler.getMaxDepth();

        // assert
        assertEquals(0, maxDepth);
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
    public void next() throws MalformedURLException, NextPageDoesNotExistsException, IOException {
        // arrange
        HttpConnection connection = mock(HttpConnection.class);
        BFSCrawler crawler = new BFSCrawler(url, connection);

        Document document = Jsoup.parse(indexHtml, url);

        // define return value for method get()
        when(connection.url(url)).thenReturn(connection);
        when(connection.get()).thenReturn(document);

        // act
        Document doc = crawler.next();

        // assert
        assertEquals(1, crawler.getMaxDepth());
        assertTrue(doc.toString().equals(document.toString()));
    }

    /**
     *
     * test if crawler has next page to crawl
     * max depth is 0 therefore should have next page
     *
     * @type direct
     * @throws MalformedURLException
     * @result true, crawler should have page to continue with
     */
    @Test
    public void hasNextNotStarted() throws MalformedURLException {
        // arrange
        HttpConnection connection = mock(HttpConnection.class);
        BFSCrawler crawler = new BFSCrawler(url, connection);

        // act
        boolean hasNext = crawler.hasNext();

        // assert
        assertTrue(hasNext);
    }

    /**
     *
     * test if crawler has next page to crawl
     * check for second condition, currentPages
     *
     * @type direct
     * @throws MalformedURLException
     * @result true, crawler should have page to continue with
     */
    @Test
    public void hasNextHasCurrentPages() throws MalformedURLException {
        // arrange
        HttpConnection connection = mock(HttpConnection.class);

        LinkedList<String> visitedPages = new LinkedList<>();
        LinkedList<String> currentPages = new LinkedList<>();
        LinkedList<String> nextPages = new LinkedList<>();

        visitedPages.add(url);
        currentPages.add("http://i296827.hera.fhict.nl/catalog.php");
        currentPages.add("http://i296827.hera.fhict.nl/catalog.php?cat=books");

        BFSCrawler crawler = new BFSCrawler(url, connection, visitedPages, currentPages, nextPages, 1);

        // act
        boolean hasNext = crawler.hasNext();

        // assert
        assertTrue(hasNext);
    }

    /**
     *
     * test if crawler has next page to crawl
     * check for third condition, currentPages
     *
     * @type direct
     * @throws MalformedURLException
     * @result true, crawler should have page to continue with
     */
    @Test
    public void hasNextPages() throws MalformedURLException {
        // arrange
        HttpConnection connection = mock(HttpConnection.class);

        LinkedList<String> visitedPages = new LinkedList<>();
        LinkedList<String> currentPages = new LinkedList<>();
        LinkedList<String> nextPages = new LinkedList<>();

        visitedPages.add(url);
        nextPages.add("http://i296827.hera.fhict.nl/catalog.php");
        nextPages.add("http://i296827.hera.fhict.nl/catalog.php?cat=books");
        nextPages.add("http://i296827.hera.fhict.nl/catalog.php?cat=movies");
        nextPages.add("http://i296827.hera.fhict.nl/catalog.php?cat=music");

        BFSCrawler crawler = new BFSCrawler(url, connection, visitedPages, currentPages, nextPages, 1);

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

        LinkedList<String> visitedPages = new LinkedList<>();
        LinkedList<String> currentPages = new LinkedList<>();
        LinkedList<String> nextPages = new LinkedList<>();

        visitedPages.add(url);

        BFSCrawler crawler = new BFSCrawler(url, connection, visitedPages, currentPages, nextPages, 1);

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
        BFSCrawler crawler = new BFSCrawler(url, connection);

        // act
        boolean matchingDomain = crawler.matchingDomain(new URL(url).getHost());
        boolean matchingDomainWWW = crawler.matchingDomain(new URL("http://www.test.url/").getHost());

        // assert
        assertTrue(matchingDomain);
        assertTrue(matchingDomainWWW);
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
        BFSCrawler crawler = new BFSCrawler(url, connection);

        // act
        boolean matchingDomain = crawler.matchingDomain(new URL("http://fail.com").getHost());
        boolean matchingDomainWWW = crawler.matchingDomain(new URL("http://www.fail.com/").getHost());

        // assert
        assertFalse(matchingDomain);
        assertFalse(matchingDomainWWW);
    }

    @Test()
    public void testExtractLinks() throws MalformedURLException, IOException {
        // arrange
        // create mock
        HttpConnection connection = mock(HttpConnection.class);
        Document document = mock(Document.class);

        Elements links = Jsoup.parse(indexHtml, url).select("a[href]");

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

        // define return value for method select("a[href]")
        when(document.select("a[href]")).thenReturn(links);

        // act
        LinkedList<String> urls = crawler.extractLinks(document);

        // assert
        assertThat(urls, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedLinks.toArray()));
    }

    @Test()
    public void getVisitedPagesCount() throws MalformedURLException, IOException {
        // arrange
        HttpConnection connection = mock(HttpConnection.class);

        LinkedList<String> visitedPages = new LinkedList<>();
        LinkedList<String> currentPages = new LinkedList<>();
        LinkedList<String> nextPages = new LinkedList<>();

        visitedPages.add(url);
        visitedPages.add("http://i296827.hera.fhict.nl/catalog.php");
        visitedPages.add("http://i296827.hera.fhict.nl/catalog.php?cat=books");
        visitedPages.add("http://i296827.hera.fhict.nl/catalog.php?cat=movies");
        visitedPages.add("http://i296827.hera.fhict.nl/catalog.php?cat=music");

        BFSCrawler crawler = new BFSCrawler(url, connection, visitedPages, currentPages, nextPages, 1);

        // act
        int nrOfVisitedPages = crawler.getVisitedPagesCount();

        // assert
        assertEquals(5, nrOfVisitedPages);
    }

    @Test()
    public void cutWww() throws MalformedURLException, IOException {
        // arrange
        HttpConnection connection = mock(HttpConnection.class);
        BFSCrawler crawler = new BFSCrawler(url, connection);

        // act
        String host = crawler.cutWww("www.test.com");

        // assert
        assertEquals("test.com", host);
    }

    @Test
    public void isStartingUrl() throws MalformedURLException, IOException {
    // arrange
    HttpConnection connection = mock(HttpConnection.class);
    BFSCrawler crawler = new BFSCrawler(url, connection);

    // act
    boolean isStartingUrl = crawler.isStartingUrl(url);
    boolean isNotStartingUrl = crawler.isStartingUrl("http://fail.com/");

    // assert
    assertTrue(isStartingUrl);
    assertFalse(isNotStartingUrl);

    }

    @Test
    public void canAddUrl() throws IOException{
        // arrange
        HttpConnection connection = mock(HttpConnection.class);

        LinkedList<String> visitedPages = new LinkedList<>();
        LinkedList<String> currentPages = new LinkedList<>();
        LinkedList<String> nextPages = new LinkedList<>();

        visitedPages.add(url);
        visitedPages.add("http://test.nl/catalog.php");
        currentPages.add("http://test.nl/catalog.php?cat=books");
        nextPages.add("http://test.nl/catalog.php?cat=movies");

        BFSCrawler crawler = new BFSCrawler(url, connection, visitedPages, currentPages, nextPages, 1);

        // act, assert
        assertFalse(crawler.canAddUrl("http://test.nl/catalog.php"));
        assertFalse(crawler.canAddUrl("http://test.nl/catalog.php?cat=books"));
        assertFalse(crawler.canAddUrl("http://test.nl/catalog.php?cat=movies"));
        assertTrue(crawler.canAddUrl("http://test.nl/catalog.php?cat=music"));
    }

    @Test(expected = UnknownHostException.class)
    public void getDocumentConnectionFail() throws MalformedURLException, IOException {
        // arrange
        HttpConnection connection = mock(HttpConnection.class);
        BFSCrawler crawler = new BFSCrawler(url, connection);

        // define return value for method get()
        when(connection.url(url)).thenReturn(connection);
        when(connection.get()).thenAnswer(invocation -> { throw new UnknownHostException(); });

        // act
        crawler.getDocument(new URL(url));

        // assert
        // throws exception
    }

}