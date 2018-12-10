package service;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.*;

public class CrawlerExecuterTest {

    CrawlerExecuter myCrawlerExecuter;
    @ParameterizedTest(name = "{index} => a={0}, b={1}")
    @CsvSource({
            "0,1",
            "1,10",
            "2,20"
    })
    public void testRun(int a, int b) throws Exception {
        myCrawlerExecuter = new CrawlerExecuter();
        myCrawlerExecuter.run(a, "http://i296827.hera.fhict.nl");
        assertEquals(b, CrawledData.myCrawledURLList.size());
    }
}