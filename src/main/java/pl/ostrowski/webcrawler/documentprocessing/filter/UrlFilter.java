package pl.ostrowski.webcrawler.documentprocessing.filter;

import org.jsoup.nodes.Document;

import java.util.Set;

public interface UrlFilter {
    Set<String> filter(Document doc);
}
