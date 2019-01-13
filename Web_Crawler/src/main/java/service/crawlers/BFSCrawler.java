package service.crawlers;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class BFSCrawler implements Crawler {
    private URL startingUrl;
    private Connection connection;
    private List<String> visitedPages;
    private List<String> currentPages;
    private List<String> nextPages;
    private int maxDepth = 0;

    BFSCrawler(String startingUrl, Connection connection) throws MalformedURLException {
        this.connection = connection;
        this.startingUrl = new URL(startingUrl);
        this.visitedPages = new LinkedList<>();
        this.currentPages = new LinkedList<>();
        this.nextPages = new LinkedList<>();
    }

    public URL getStartingUrl() {
        return startingUrl;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    @Override
    public Document next() throws NextPageDoesNotExistsException {
        return null;
    }

    /**
     *
     * TODO: pass document directly, this way we can mock document and set method select("a[href]") to get directly
     * list of known results, otherwise
     *
     * @param url
     * @return
     * @throws IOException
     */
    public LinkedList<String> extractLinks(URL url) throws IOException {
        LinkedList<String> validLinks = new LinkedList<>();

        return validLinks;
    }

    private boolean isStartingUrl(String url) {
        return true;
    }

    private boolean canAddUrl(String url) {

        return true;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public boolean matchingDomain(String url) {
        return false;
    }

    private int getVisitedPagesCount() {
        return this.visitedPages.size();
    }

    /**
     *
     * adds new links to unique stack of pages to be crawled
     *
     * @param pageUrls list of url links to add
     */
    public void addPagesToVisit(LinkedList<String> pageUrls) {

    }

    public boolean isDomainUrl(String host) {

        return true;
    }

    public String cutWww(String host) {
        return host;
    }

    private void switchNextPagesToCurrentPages() {

    }
}
