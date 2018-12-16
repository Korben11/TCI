package service.crawlers;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface Crawler {

    /**
     * this method will retrieve html from next page
     *
     * @return returns instance of jsoup document
     * @throws NextPageDoesNotExistsException
     */
    Document next() throws NextPageDoesNotExistsException, IOException;

    /**
     *
     * @return true, if there is next page to crawl, otherwise false
     */
    boolean hasNext();

    /**
     *
     * Check if url domain is matching startUrl domain.
     *
     * @return true if is matching, otherwise false
     */
    boolean matchingDomain(String url);
}
