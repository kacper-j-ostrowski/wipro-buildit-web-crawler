package pl.ostrowski.webcrawler.datamodel;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class WebCrawlerVisitsHistory {

        private final Set<WebCrawledSite> historyOfVisits;

        public WebCrawlerVisitsHistory() {
            this.historyOfVisits = new HashSet<>();
        }

        public boolean siteWasAlreadyProcessed(String siteUrl) {
            return historyOfVisits.contains(new WebCrawledSite(siteUrl, null));
        }

        public void addProcessedSite(WebCrawledSite processedSite) {
            if(!siteWasAlreadyProcessed(processedSite.getPageUrlAddress())) {
                historyOfVisits.add(processedSite);
            }
        }

        public Set<WebCrawledSite> getHistoryOfVisits() {
            return Collections.unmodifiableSet(historyOfVisits);
        }
}
