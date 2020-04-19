package pl.ostrowski.webcrawler.logger;

public class SimpleLogger {

    public void log(String message) {
        System.out.println(message);
    }

    public void log(String message, Exception e) {
        System.out.println(message + " " + e.getMessage());
    }

}
