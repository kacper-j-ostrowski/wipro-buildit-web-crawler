package pl.ostrowski.webcrawler.datamodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebCrawlerVisitsHistoryTest {

    private WebCrawlerVisitsHistory webCrawlerVisitsHistory;

    @BeforeEach
    void setUp() {
        webCrawlerVisitsHistory = new WebCrawlerVisitsHistory();
    }

    @Test
    void whenSiteAlreadyExists_thenDoNotAddIt() {
        // given
        WebCrawledSite webCrawledSite_1 = new WebCrawledSite("http://testpage.pl", null);
        WebCrawledSite webCrawledSite_2 = new WebCrawledSite("http://anothertestpage.pl", null);
        WebCrawledSite webCrawledSite_3 = new WebCrawledSite("http://testpage.pl", null);

        // when
        webCrawlerVisitsHistory.addProcessedSite(webCrawledSite_1);
        webCrawlerVisitsHistory.addProcessedSite(webCrawledSite_2);
        webCrawlerVisitsHistory.addProcessedSite(webCrawledSite_3);

        // then
        assertEquals(2, webCrawlerVisitsHistory.getHistoryOfVisits().size());
    }

}