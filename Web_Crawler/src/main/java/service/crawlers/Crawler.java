package service.crawlers;

import org.jsoup.nodes.Document;

public interface Crawler {
    /**
     * this method will retrieve html from next page
     *
     * @return returns instance of jsoup document
     * @throws NextPageDoesNotExistsException
     */
    Document next() throws NextPageDoesNotExistsException;

    /**
     *
     * @return
     */
    boolean hasNext();
}
