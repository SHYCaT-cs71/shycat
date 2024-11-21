package fyi.shycat.site.rest;

import fyi.shycat.site.entities.Event;
import fyi.shycat.site.repositories.EventRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("events")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("")
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }
}
