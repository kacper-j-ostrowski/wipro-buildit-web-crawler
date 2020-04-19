package pl.ostrowski.webcrawler.documentprocessing;

import org.jsoup.nodes.Document;
import pl.ostrowski.webcrawler.documentprocessing.filter.UrlFilter;

import java.util.Set;

public class UrlsRetrieveOperation {

    private Set<UrlFilter> filters;

    public UrlsRetrieveOperation() {
        this.filters = UrlFiltersProvider.provide();
    }

    public Set<String> retrieve(final Document doc) {
        return filters.
                stream()
                .map(f -> f.filter(doc))
                .reduce((s1, s2) -> {
                    s1.addAll(s2);
                    return s1;
                })
                .get();
    }
}
