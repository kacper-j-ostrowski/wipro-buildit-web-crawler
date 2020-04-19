package pl.ostrowski.webcrawler;

import pl.ostrowski.webcrawler.engine.DomainNarrowedCrawler;

public class WebCrawlerApp {

    public static void main(String[] args) {
        DomainNarrowedCrawler domainNarrowedCrawler = new DomainNarrowedCrawler("https://wiprodigital.com/");
        domainNarrowedCrawler.process();
    }

}
