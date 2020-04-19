package pl.ostrowski.webcrawler.documentprocessing.filter;

import org.jsoup.nodes.Document;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StyleFilter implements UrlFilter {

    private static final String SRC = "[style]";
    final Pattern URL_PATTERN = Pattern.compile("url\\((.*?)\\)");

    @Override
    public Set<String> filter(Document doc) {
        return doc.select(SRC)
                .stream()
                .map(e -> e.attr("style"))
                .map(this::retrieveUrlFromStyle)
                .collect(Collectors.toSet());
    }

    private String retrieveUrlFromStyle(String input) {
        Matcher m = URL_PATTERN.matcher(input);
        String url = "";
        while (m.find()) {
            url = m.group(1);
        }
        if(url.startsWith("\"")) {
            url = url.replaceAll("\"", "");
        }
        if(url.startsWith("'")) {
            url = url.replaceAll("'", "");
        }
        return url;
    }
}
