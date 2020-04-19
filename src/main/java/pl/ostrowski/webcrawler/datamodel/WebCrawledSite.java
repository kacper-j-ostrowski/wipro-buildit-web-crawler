package pl.ostrowski.webcrawler.datamodel;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class WebCrawledSite {
    private final String pageUrlAddress;
    private final Set<String> externalConnections;

    public WebCrawledSite(String pageUrlAddress, Set<String> externalConnections) {
        this.pageUrlAddress = pageUrlAddress;
        this.externalConnections = externalConnections;
    }

    public Set<String> getExternalConnections() {
        return Collections.unmodifiableSet(externalConnections);
    }

    public String getPageUrlAddress() {
        return pageUrlAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebCrawledSite that = (WebCrawledSite) o;
        return pageUrlAddress.equals(that.pageUrlAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageUrlAddress);
    }
}
