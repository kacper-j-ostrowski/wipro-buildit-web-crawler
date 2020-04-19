package pl.ostrowski.webcrawler.engine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import pl.ostrowski.webcrawler.datamodel.WebCrawledSite;
import pl.ostrowski.webcrawler.datamodel.WebCrawlerVisitsHistory;
import pl.ostrowski.webcrawler.documentprocessing.UrlsRetrieveOperation;
import pl.ostrowski.webcrawler.logger.SimpleLogger;
import pl.ostrowski.webcrawler.printer.ResultPrinter;
import pl.ostrowski.webcrawler.urlnormalize.NormalizedUrl;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DomainNarrowedCrawler implements WebCrawlingEngine {

    private static final SimpleLogger logger = new SimpleLogger();

    private final String baseDomain;
    private final ResultPrinter resultPrinter;
    private final NormalizedUrl normalizedUrl;
    private final UrlsRetrieveOperation urlsRetrieveOperation;
    private final WebCrawlerVisitsHistory webCrawlerVisitsHistory;
    private final Set<String> websitesToProcess;
    private final Predicate<String> siteIsWithinWiproBuildItDomain;

    public DomainNarrowedCrawler(String domain) {
        this.baseDomain = domain;
        this.webCrawlerVisitsHistory = new WebCrawlerVisitsHistory();
        this.websitesToProcess = new TreeSet<>();
        this.urlsRetrieveOperation = new UrlsRetrieveOperation();
        this.normalizedUrl = new NormalizedUrl();
        this.resultPrinter = new ResultPrinter();
        this.siteIsWithinWiproBuildItDomain = s -> s.startsWith(domain);
    }

    @Override
    public void process() {
        long startTime = System.nanoTime();
        logger.log("Start to process crawling for domain: " + baseDomain);
        websitesToProcess.add(baseDomain);

        while (!websitesToProcess.isEmpty()) {
            String nextSiteToProcess = websitesToProcess.iterator().next();
            Document website = null;

            logger.log("Started processing URL: " + nextSiteToProcess);

            try {
                website = Jsoup.connect(nextSiteToProcess).get();
            } catch (IOException e) {
                logger.log("Exceptions while parsing site", e);
            }

            if (website != null) {
                Set<String> externalLinks = retrieveAllExternalLinks(website);
                Set<String> externalLinksInDomainNotProcessed = externalLinks.stream()
                        .filter(siteIsWithinWiproBuildItDomain)
                        .map(normalizedUrl::normalizeUrl)
                        .filter(s -> !s.isEmpty())
                        .filter(s -> !webCrawlerVisitsHistory.siteWasAlreadyProcessed(s))
                        .collect(Collectors.toSet());

                webCrawlerVisitsHistory.addProcessedSite(new WebCrawledSite(nextSiteToProcess, externalLinks));
                websitesToProcess.addAll(externalLinksInDomainNotProcessed);
            }

            logger.log("Finished processing URL: " + nextSiteToProcess);
            websitesToProcess.remove(nextSiteToProcess);
        }
        logger.log("Finished processing with time: " + (System.nanoTime() - startTime) + " nanoseconds");
        resultPrinter.print(webCrawlerVisitsHistory);
    }

    private Set<String> retrieveAllExternalLinks(Document doc) {
        return urlsRetrieveOperation.retrieve(doc);
    }
}
