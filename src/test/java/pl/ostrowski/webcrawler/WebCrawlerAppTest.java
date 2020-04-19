package pl.ostrowski.webcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WebCrawlerAppTest {

    @Test
    void test() throws IOException {
        final String buildItWiproBaseUrl = "https://wiprodigital.com/";
        Document doc = Jsoup.connect(buildItWiproBaseUrl).get();
//        Elements links = doc.select("[src]");
//        links.forEach(System.out::println);
//
//        Elements hrefs = doc.select("[href]");
//        hrefs.forEach(System.out::println);

        Elements urls = doc.select("[style*='url']");

        urls.forEach(System.out::println);

    }


}