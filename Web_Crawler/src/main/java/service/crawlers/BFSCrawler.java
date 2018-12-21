package service.crawlers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class BFSCrawler implements Crawler {
    public URL getStartingUrl() {
        return startingUrl;
    }

    private URL startingUrl;
    private List<String> visitedPages;
//    private Set<String> visitedPages;
    private List<String> currentPages;
    private List<String> nextPages;
    private int maxDepth = 0;

    public BFSCrawler(String startingUrl) throws MalformedURLException {
        this.startingUrl = new URL(startingUrl);
        this.visitedPages = new LinkedList<>();
        this.currentPages = new LinkedList<>();
        this.nextPages = new LinkedList<>();
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    @Override
    public Document next() throws NextPageDoesNotExistsException {
        return null;
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

    private void switchNextPagesToCurrentPages() {

    }
}
