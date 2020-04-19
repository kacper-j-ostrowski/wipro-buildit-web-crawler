package pl.ostrowski.webcrawler.documentprocessing.filter;

import org.jsoup.nodes.Document;

import java.util.Set;
import java.util.stream.Collectors;

public class SrcFilter implements UrlFilter {

    private static final String SRC = "[src]";

    @Override
    public Set<String> filter(Document doc) {
        return doc.select(SRC)
                .stream()
                .map(e -> e.attr("src"))
                .collect(Collectors.toSet());
    }
}
