package pl.ostrowski.webcrawler.documentprocessing.filter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UrlFilterTestDifferentTypesOfFilters {

    private final HrefFilter hrefFilter = new HrefFilter();
    private final SrcFilter srcFilter = new SrcFilter();
    private final StyleFilter styleFilter = new StyleFilter();

    private Document doc;

    @BeforeEach
    void setupTestData() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File testHtml = new File(classLoader.getResource("testHtml.html").getFile());
        doc = Jsoup.parse(testHtml, "UTF-8");
    }

    @Test
    void whenHrefFilterApplied_ShouldReturnAllHrefLinks() {
        // when
        Set<String> res = hrefFilter.filter(doc);

        Set<String> expectedSet = new HashSet<>();
        expectedSet.add("https://www.wiprotest.pl");
        expectedSet.add("https://www.wiprotest2.pl");

        // then
        assertEquals(2, res.size());
        assertEquals(expectedSet, res);
    }

    @Test
    void whenSrcFilterApplied_ShouldReturnAllSrcLinks() {
        // when
        Set<String> res = srcFilter.filter(doc);

        // then
        assertEquals(1, res.size());
        assertEquals("https://www.script.pl", res.iterator().next());
    }

    @Test
    void whenStyleFilterApplied_ShouldReturnAllStyleLinks() {
        // when
        Set<String> res = styleFilter.filter(doc);

        // then
        assertEquals(1, res.size());
        assertEquals("https://www.resources.com", res.iterator().next());
    }

}