package fyi.shycat.site.web_scraping;

import fyi.shycat.site.entities.Event;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface WebScraper {

    List<Event> getEvents(LocalDate date) throws IOException;
}
