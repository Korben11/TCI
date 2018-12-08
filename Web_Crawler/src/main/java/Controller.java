import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "/home/korben/Documents/crawl   ";
        int numberOfCrawlers = 1;


        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        controller.addSeed("http://i296827.hera.fhict.nl");

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(MyCrawler.class, numberOfCrawlers);
        parseUrlToJSON((ArrayList<String>) CrawledData.myCrawledURLList);
    }

    private static void parseUrlToJSON(ArrayList<String> list) throws IOException {
        Document doc;
        for (String s : list) {
            doc = Jsoup.connect(s).get();
            log(doc.title());
            Elements headlines = doc.select("div.media-details");
            for (Element headline : headlines) {
                if (headline.childNode(3).toString().contains("Book")) {
                    CrawledData.myCrawledBooks.add(doc.title() + headline.childNode(3).childNode(1).childNode(2).toString());
                } else if (headline.childNode(3).toString().contains("Music")) {
                    CrawledData.myCrawledBooks.add(doc.title() + headline.childNode(3).childNode(1).childNode(2).toString());
                } else if (headline.childNode(3).toString().contains("Movie")) {
                    CrawledData.myCrawledBooks.add(doc.title() + headline.childNode(3).childNode(1).childNode(2).toString());
                }
            }
        }
        for (String book : CrawledData.myCrawledBooks) {
            System.out.println(book);
        }
        for (String music : CrawledData.myCrawledMusic) {
            System.out.println(music);
        }
        for (String movie : CrawledData.myCrawledMovies) {
            System.out.println(movie);
        }
    }


    private static void log(String title) {
        CrawledData.myCrawledJSONlist.add(title);
    }
}
