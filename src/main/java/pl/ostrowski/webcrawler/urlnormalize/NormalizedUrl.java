package pl.ostrowski.webcrawler.urlnormalize;

import java.net.URI;
import java.net.URISyntaxException;

public class NormalizedUrl {

    public String normalizeUrl(String urlToNormalize) {
        URI uri = null;
        try {
            uri = new URI(urlToNormalize);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (uri != null) {
            uri = uri.normalize();
            if (urlToNormalize.startsWith("https")) {
                urlToNormalize = "https://" + uri.getRawAuthority() + uri.getRawPath();
            } else {
                urlToNormalize = "http://" + uri.getRawAuthority() + uri.getRawPath();
            }
        }
        if (urlToNormalize.endsWith("/")) {
            urlToNormalize = urlToNormalize.substring(0, urlToNormalize.length() - 1);
        }
        return urlToNormalize;
    }

}
