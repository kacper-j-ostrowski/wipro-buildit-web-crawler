package pl.ostrowski.webcrawler.documentprocessing;

import pl.ostrowski.webcrawler.documentprocessing.filter.HrefFilter;
import pl.ostrowski.webcrawler.documentprocessing.filter.SrcFilter;
import pl.ostrowski.webcrawler.documentprocessing.filter.StyleFilter;
import pl.ostrowski.webcrawler.documentprocessing.filter.UrlFilter;

import java.util.HashSet;
import java.util.Set;

public class UrlFiltersProvider {

    static Set<UrlFilter> filters;

    static {
        filters = new HashSet<>();
        filters.add(new HrefFilter());
        filters.add(new SrcFilter());
        filters.add(new StyleFilter());
    }

    public static Set<UrlFilter> provide() {
        return filters;
    }
}
