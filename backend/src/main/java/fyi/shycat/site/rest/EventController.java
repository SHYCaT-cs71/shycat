package fyi.shycat.site.rest;

import fyi.shycat.site.entities.Event;
import fyi.shycat.site.repositories.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController()
@RequestMapping("events")
public class EventController {

    private final EventRepository eventRepository;
    private static final Logger LOG = LoggerFactory.getLogger(EventController.class);

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("")
    public List<Event> getEvents() {
        LOG.debug("getEvents");
        // Use yesterday as the cutoff since it excludes anything starting on the specified date -
        // this way we'll get events starting today as well
        return eventRepository.getEventsByStartDateTime_DateAfter(LocalDate.now().minusDays(1));
    }

    @GetMapping("{id}")
    public Event getEvent(@PathVariable("id") long id) {
        LOG.debug("getEvent {}", id);
        return eventRepository.findById(id).orElse(null);
    }
}
