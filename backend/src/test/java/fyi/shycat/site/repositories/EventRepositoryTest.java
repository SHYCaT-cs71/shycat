package fyi.shycat.site.repositories;

import fyi.shycat.site.entities.DateTime;
import fyi.shycat.site.entities.Event;
import fyi.shycat.site.entities.Location;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EventRepositoryTest {

    @Inject
    private EventRepository eventRepository;

    @Test
    void testSave() {
        var event = createEvent(null, "Harvard Startup Showcase");
        assertThat(event.getId(), is(nullValue()));

        eventRepository.save(event);
        assertThat(event.getId(), is(notNullValue()));

        Optional<Event> savedEventOpt = eventRepository.findById(event.getId());
        assertThat(savedEventOpt.isPresent(), is(true));
        var savedEvent = savedEventOpt.get();
        assertThat(savedEvent.getOriginalId(), is(event.getOriginalId()));
        assertThat(savedEvent.getTitle(), is(event.getTitle()));
        assertThat(savedEvent.getSummary(), is(event.getSummary()));
        assertThat(savedEvent.getDescription(), is(event.getDescription()));
        assertThat(savedEvent.getStartDateTime(), is(event.getStartDateTime()));
        assertThat(savedEvent.getEndDateTime(), is(event.getEndDateTime()));
        assertThat(savedEvent.getLocation(), is(event.getLocation()));
        assertThat(savedEvent.getHost(), is(event.getHost()));
        assertThat(savedEvent.getOriginalLink(), is(event.getOriginalLink()));
        assertThat(savedEvent.getImageUrl(), is(event.getImageUrl()));
        assertThat(savedEvent.getTags(), is(event.getTags()));
    }

    @Test
    void testGet_FullLocation_DateTime() {
        var result = eventRepository.findById(1L);
        assertThat(result.isPresent(), is(true));
        checkEvent(result.get());
    }

    @Test
    void testGet_LocationNameOnly() {
        var result = eventRepository.findById(3L);
        assertThat(result.isPresent(), is(true));

        var event = result.get();
        assertThat(event.getLocation(), is(notNullValue()));
        // Just check location, other property load is already checked
        var location = event.getLocation();
        assertThat(location.getName(), is("Harvard Hall, Cambridge, MA"));
        assertThat(location.getAddress(), is(nullValue()));
        assertThat(location.getGeo(), is(nullValue()));
    }

    @Test
    void testGet_NoLocation_DateOnly() {
        var result = eventRepository.findById(5L);
        assertThat(result.isPresent(), is(true));

        var event = result.get();
        // Just check location and start/end times, other property load is already checked
        assertThat(event.getLocation(), is(nullValue()));
        assertThat(event.getStartDateTime().getDate(), is(LocalDate.of(2025, 2, 20)));
        assertThat(event.getStartDateTime().getTime(), is(nullValue()));
        assertThat(event.getEndDateTime().getDate(), is(LocalDate.of(2025, 2, 22)));
        assertThat(event.getEndDateTime().getTime(), is(nullValue()));
    }

    @Test
    void testGetEventByOriginalId() {
        var result = eventRepository.getEventByOriginalId("10001");
        assertThat(result.isPresent(), is(true));
        checkEvent(result.get());

        var result2 = eventRepository.getEventByOriginalId("nonsense");
        assertThat(result2.isPresent(), is(false));
    }

    void checkEvent(Event event) {
        assertThat(event.getOriginalId(), is("10001"));
        assertThat(event.getTitle(), is("Test title"));
        assertThat(event.getSummary(), is("An exciting showcase where Harvard entrepreneurs and innovators present " +
                                          "their startup ideas."));
        assertThat(event.getDescription(), is("This annual event brings together the Harvard community to witness and support the entrepreneurial ideas of Harvard's brightest minds. Innovators pitch their projects to potential investors, gaining invaluable feedback and visibility. From tech startups to social enterprises, attendees will see a diverse array of ventures aimed at addressing real-world issues. It’s also a great networking opportunity for anyone interested in entrepreneurship and innovation."));
        assertThat(event.getStartDateTime().getDate(), is(LocalDate.of(2024, 11, 30)));
        assertThat(event.getStartDateTime().getTime(),
                   is(LocalTime.of(18, 0, 0)));
        assertThat(event.getEndDateTime().getDate(), is(LocalDate.of(2024, 11, 30)));
        assertThat(event.getEndDateTime().getTime(),
                   is(LocalTime.of(20, 30, 0)));
        assertThat(event.getHost(), is("Harvard Innovation Labs"));
        assertThat(event.getLocation(), is(notNullValue()));
        var location = event.getLocation();
        assertThat(location.getType(), is("Place"));
        assertThat(location.getName(), is("Harvard i-lab, Batten Hall"));
        assertThat(location.getAddress(), is("125 Western Ave, Allston"));
        assertThat(location.getLocationUrl(), is("https://nonsense.com"));
        assertThat(location.getGeo(), is(notNullValue()));
        assertThat(location.getGeo().getLatitude(), is(closeTo(42.36405444183414, 1e-8)));
        assertThat(location.getGeo().getLongitude(), is(closeTo(-71.12418004499675, 1e-8)));
        assertThat(event.getImageUrl(), is("https://picsum.photos/800/600?random=0.1"));
        assertThat(event.getOriginalLink(), is("https://example.com/harvard-startup-showcase"));
        assertThat(event.getTags(), containsInAnyOrder("Entrepreneurship","Startups","Networking"));
    }

    @Test
    void testFindAll(){
        var eventList = eventRepository.findAll();
        assertThat(eventList, hasSize(5));
        eventList.sort(Comparator.comparing(Event::getId));
        checkEvent(eventList.getFirst());
    }

    @Test
    void testGetEventsBy_StartDateTimeDateAfter() {
        // Cutoff matches start date of event with time
        var resultA = eventRepository.getEventsByStartDateTime_DateAfter(LocalDate.of(2024, 12, 5));
        assertThat(resultA, hasSize(3));

        // Cutoff matches start date of event without time
        var resultB = eventRepository.getEventsByStartDateTime_DateAfter(LocalDate.of(2024, 12, 10));
        assertThat(resultB, hasSize(2));
    }

    public static Event createEvent(Long id, String title) {
        var event = new Event();
        event.setId(id);
        event.setOriginalId("505050");
        event.setTitle(title);
        event.setSummary("Summary: " + title);
        event.setDescription("Description: " + title);
        event.setStartDateTime(new DateTime(LocalDate.of(2024, 11, 25),
                                            LocalTime.of(19, 20, 47)));
        event.setEndDateTime(new DateTime(LocalDate.of(2024, 11, 25),
                                          LocalTime.of(22, 5, 30)));

        event.setHost("Harvard Innovation Labs");
        event.setOriginalLink("https://example.com/harvard-startup-showcase");
        event.setTags(Set.of("Entrepreneurship", "Startups", "Networking"));
        event.setImageUrl("https://picsum.photos/800/600?random=1003");

        var location = new Location();
        location.setType("place");
        location.setName("Sanders Theatre, Cambridge, MA");
        location.setAddress("11 Divinity Avenue, Cambridge");
        location.setLocationUrl("https://nonsense.com");
        var geo = new Location.GeoLocation(42.377512, -71.114127);
        location.setGeo(geo);
        event.setLocation(location);
        return event;
    }
}
