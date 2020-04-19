package pl.ostrowski.webcrawler.documentprocessing.filter;

import org.jsoup.nodes.Document;

import java.util.Set;
import java.util.stream.Collectors;

public class HrefFilter implements UrlFilter {

    private static final String HREF = "[href]";

    @Override
    public Set<String> filter(Document doc) {
        return doc.select(HREF)
                .stream()
                .map(e -> e.attr("href"))
                .collect(Collectors.toSet());
    }
}
