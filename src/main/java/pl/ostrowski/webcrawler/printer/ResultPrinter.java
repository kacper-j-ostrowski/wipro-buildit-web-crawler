package pl.ostrowski.webcrawler.printer;

import pl.ostrowski.webcrawler.datamodel.WebCrawledSite;
import pl.ostrowski.webcrawler.datamodel.WebCrawlerVisitsHistory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ResultPrinter {

    private static final String FILE_PATH = "results.txt";

    public void print(WebCrawlerVisitsHistory webCrawlerVisitsHistory) {

        File result = new File(FILE_PATH);
        try {
            FileWriter fr = new FileWriter(result);

            for (WebCrawledSite entry : webCrawlerVisitsHistory.getHistoryOfVisits()) {
                fr.append("# " + entry.getPageUrlAddress());
                fr.append("\n");
                entry.getExternalConnections()
                        .forEach(e -> {
                            try {
                                fr.append("### " + e);
                                fr.append("\n");
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
