package service.crawlers;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
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

    BFSCrawler(String startingUrl, Connection connection, LinkedList<String> visitedPages, LinkedList<String> nextPages, LinkedList<String> currentPages, int maxDepth) throws MalformedURLException {
        this.connection = connection;
        this.startingUrl = new URL(startingUrl);
        this.visitedPages = visitedPages;
        this.currentPages = currentPages;
        this.nextPages = nextPages;
        this.maxDepth = maxDepth;
    }

    public URL getStartingUrl() {
        return startingUrl;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    @Override
    public Document next() throws NextPageDoesNotExistsException, IOException {
        if (maxDepth==0) {
            this.maxDepth += 1;
            Document doc = this.getDocument(this.startingUrl);
            this.currentPages = this.extractLinks(doc);
            return doc;
        }
        if (this.currentPages.size() > 0) {
            return this.popFromCurrentPages();
        }
        this.switchNextPagesToCurrentPages();
        return this.popFromCurrentPages();
    }

    private Document popFromCurrentPages() throws IOException {
        Document doc= this.getDocument(new URL(this.currentPages.get(0)));
        this.currentPages.remove(0);
        this.nextPages.addAll(this.extractLinks(doc));
        return doc;
    }

    /**
     *
     * Extracts all url links from given jsoup Document
     * Checks every url for presence in existing lists
     * Checks if url is fomain related
     *
     * @param document
     * @return Return LinkedList<String> with new url to be crawled
     * @throws IOException
     */
    public LinkedList<String> extractLinks(Document document) throws IOException {
        LinkedList<String> validLinks = new LinkedList<>();

        Elements links = document.select("a[href]");

        for (Element link : links) {
            URL aURL = new URL(link.attr("abs:href"));
            String fullUrl = link.attr("abs:href");
            if (!this.matchingDomain(aURL.getHost())) { continue; }
            if (this.isStartingUrl(fullUrl)) { continue; }
            if (!this.canAddUrl(fullUrl)) { continue; }
            validLinks.add(fullUrl);
        }

        return validLinks;
    }

    public Document getDocument (URL url) throws IOException, UnknownHostException {
        this.visitedPages.add(url.toString());
        this.connection = connection.url(this.startingUrl.toString());
        return this.connection.get();
    }

    public boolean isStartingUrl(String url) {
        return url.equals(this.startingUrl.toString());
    }

    public boolean canAddUrl(String url) {
        for (String link: this.visitedPages) {
            if (link.equals(url)) { return false; }
        }
        for (String link: this.currentPages) {
            if (link.equals(url)) { return false; }
        }
        for (String link: this.nextPages) {
            if (link.equals(url)) { return false; }
        }

        return true;
    }

    @Override
    public boolean hasNext() {
        if (this.maxDepth == 0) { return true; }
        if (this.currentPages.size() > 0) { return true; }
        return (this.nextPages.size() > 0);
    }

    @Override
    public boolean matchingDomain(String host) {
        host = cutWww(host);
        return host.equals(cutWww(this.startingUrl.getHost()));
    }

    public int getVisitedPagesCount() {
        return this.visitedPages.size();
    }

    public String cutWww(String host) {
        return host.startsWith("www.") ? host.substring(4) : host;
    }

    /**
     *
     * moves nextPages to current pages and empties current pages
     * increase depth of crawl search
     *
     */
    private void switchNextPagesToCurrentPages() {
        this.maxDepth += 1;
        this.currentPages = this.nextPages;
        this.nextPages = new LinkedList<String>();
    }
}
