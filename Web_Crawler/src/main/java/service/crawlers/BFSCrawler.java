package service.crawlers;

import org.jsoup.nodes.Document;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BFSCrawler implements Crawler {
    private String startingUrl;
    private Set<String> visitedPages;
    private List<String> currentPages;
    private List<String> nextPages;
    private int maxDepth = 0;

    public BFSCrawler(String startingUrl) {
        this.startingUrl = startingUrl;
        this.visitedPages = new HashSet<>();
        this.currentPages = new LinkedList<>();
        this.nextPages = new LinkedList<>();
    }

    @Override
    public Document next() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    private void addVisitedPage(String pageUrl) {

    }

    private void switchNextPagesToCurrentPages() {

    }
}
