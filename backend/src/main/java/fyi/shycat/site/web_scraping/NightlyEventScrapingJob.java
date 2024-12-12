package fyi.shycat.site.web_scraping;

import fyi.shycat.site.entities.Event;
import fyi.shycat.site.repositories.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
public class NightlyEventScrapingJob {

    private static final Logger LOG = LoggerFactory.getLogger(NightlyEventScrapingJob.class);

    private static final int DAYS_AHEAD_TO_SCRAPE = 7;

    private final HarvardEventWebScraperImpl harvardEventWebScraper;

    private final EventRepository eventRepository;

    @Autowired
    public NightlyEventScrapingJob(HarvardEventWebScraperImpl harvardEventWebScraper, EventRepository eventRepository) {
        this.harvardEventWebScraper = harvardEventWebScraper;
        this.eventRepository = eventRepository;
    }

    @Scheduled(cron = "0 15 23 * * *")
    public void scrapeHarvardEvents() {
        LocalDate startDate = LocalDate.now();
        scrapeHarvardEvents(startDate, startDate.plusDays(DAYS_AHEAD_TO_SCRAPE));
    }

    @Scheduled(initialDelay = 10000)
    public void scrapeHarvardEvents_AtStartup() {
        LocalDate startDate = LocalDate.now();
        scrapeHarvardEvents(startDate, startDate.plusDays(DAYS_AHEAD_TO_SCRAPE));
    }

    int scrapeHarvardEvents(LocalDate startDate, LocalDate endDate) {
        LocalDate date = startDate;
        int eventCount = 0;

        while (date.isBefore(endDate)) {
            try {
                var events = harvardEventWebScraper.getEvents(date);
                for (Event event : events) {
                    handleScrapedEvent(event);
                }
                eventCount += events.size();
                date = date.plusDays(1);
            }
            catch (Exception e) {
                LOG.error("Error scraping Harvard events for {}", date, e);
            }
        }
        LOG.info("Scraped {} events from {} to {}", eventCount, startDate, endDate);
        return eventCount;
    }

    void handleScrapedEvent(Event event) {
        var savedEventOptional = eventRepository.getEventByOriginalId(event.getOriginalId());
        if (savedEventOptional.isPresent()) {
            var savedEvent = savedEventOptional.get();
            if (hasEventChanged(savedEvent, event)) {
                updateSavedEventWithNewValues(savedEvent, event);
                eventRepository.save(savedEvent);
            }
        }
        else {
            eventRepository.save(event);
        }
    }

    boolean hasEventChanged(Event savedEvent, Event newEvent) {
        if (!Objects.equals(newEvent.getTitle(), savedEvent.getTitle())) {
            return true;
        }
        if (!Objects.equals(newEvent.getSummary(), savedEvent.getSummary())) {
            return true;
        }
        if (!Objects.equals(newEvent.getDescription(), savedEvent.getDescription())) {
            return true;
        }
        if (!Objects.equals(newEvent.getStartDateTime(), savedEvent.getStartDateTime())) {
            return true;
        }
        if (!Objects.equals(newEvent.getEndDateTime(), savedEvent.getEndDateTime())) {
            return true;
        }
        if (!Objects.equals(newEvent.getHost(), savedEvent.getHost())) {
            return true;
        }
        if (!Objects.equals(newEvent.getOriginalLink(), savedEvent.getOriginalLink())) {
            return true;
        }
        if (!Objects.equals(newEvent.getImageUrl(), savedEvent.getImageUrl())) {
            return true;
        }
        if (!Objects.equals(newEvent.getTags(), savedEvent.getTags())) {
            return true;
        }
        if (!Objects.equals(newEvent.getLocation(), savedEvent.getLocation())) {
            return true;
        }

        return false;
    }

    void updateSavedEventWithNewValues(Event savedEvent, Event newEvent) {
        savedEvent.setTitle(newEvent.getTitle());
        savedEvent.setSummary(newEvent.getSummary());
        savedEvent.setDescription(newEvent.getDescription());
        savedEvent.setStartDateTime(newEvent.getStartDateTime());
        savedEvent.setEndDateTime(newEvent.getEndDateTime());
        savedEvent.setHost(newEvent.getHost());
        savedEvent.setOriginalLink(newEvent.getOriginalLink());
        savedEvent.setImageUrl(newEvent.getImageUrl());
        savedEvent.setTags(newEvent.getTags());
        savedEvent.setLocation(newEvent.getLocation());
    }
}
