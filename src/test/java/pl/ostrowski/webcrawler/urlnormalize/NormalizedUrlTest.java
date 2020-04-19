package pl.ostrowski.webcrawler.urlnormalize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormalizedUrlTest {

    private NormalizedUrl normalizedUrl;

    @BeforeEach
    void setNormalizedUrl() {
        normalizedUrl = new NormalizedUrl();
    }

    @Test
    void whenUrlWithParametersProvided_ThenReturnUrlWithoutParameters() {
        // given
        String urlWithParameters = "https://www.testdomain.pl/?somearg=1&someotherarg=2";

        // when
        String resUrl = normalizedUrl.normalizeUrl(urlWithParameters);

        // then
        assertEquals("https://www.testdomain.pl", resUrl);
    }

    @Test
    void whenUrlWithHashProvided_ThenReturnUrlWithoutHash() {
        // given
        String urlWithParameters = "https://www.testdomain.pl/#gotocustomid";

        // when
        String resUrl = normalizedUrl.normalizeUrl(urlWithParameters);

        // then
        assertEquals("https://www.testdomain.pl", resUrl);
    }


    @Test
    void whenUrlWithSlashInTheEndProvided_ThenReturnUrlWithoutSlashInTheEnd() {
        // given
        String urlWithParameters = "https://www.testdomain.pl/";

        // when
        String resUrl = normalizedUrl.normalizeUrl(urlWithParameters);

        // then
        assertEquals("https://www.testdomain.pl", resUrl);
    }

    @Test
    void whenCombinedUrl_ThenReturnURawUrl() {
        // given
        String urlWithParameters = "https://www.testdomain.pl/?somearg=1&someotherarg=2#gotocustomid";

        // when
        String resUrl = normalizedUrl.normalizeUrl(urlWithParameters);

        // then
        assertEquals("https://www.testdomain.pl", resUrl);
    }


}