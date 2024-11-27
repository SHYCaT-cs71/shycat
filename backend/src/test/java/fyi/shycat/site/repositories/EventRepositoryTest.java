package fyi.shycat.site.repositories;

import fyi.shycat.site.entities.DateTime;
import fyi.shycat.site.entities.Event;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Sql("/event_input.sql")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EventRepositoryTest {

    @Inject
    private EventRepository eventRepository;

    @Test
    void testSave() {
        var event = new Event();
        event.setTitle("Harvard Startup Showcase");
        event.setSummary("An exciting showcase where Harvard entrepreneurs and innovators present their startup ideas.");
        event.setDescription("This annual event brings together the Harvard community to witness and support the entrepreneurial ideas of Harvard's brightest minds. Innovators pitch their projects to potential investors, gaining invaluable feedback and visibility. From tech startups to social enterprises, attendees will see a diverse array of ventures aimed at addressing real-world issues. It’s also a great networking opportunity for anyone interested in entrepreneurship and innovation.\",\n");
        event.setStartDateTime(new DateTime(LocalDate.of(2024, 11, 25),
                                            OffsetTime.of(LocalTime.of(19, 20, 47), ZoneOffset.ofHours(-5))));
        event.setEndDateTime(new DateTime(LocalDate.of(2024, 11, 25),
                                          OffsetTime.of(LocalTime.of(22, 5, 30), ZoneOffset.ofHours(-5))));

        event.setHost("Harvard Innovation Labs");
        event.setOriginalLink("https://example.com/harvard-startup-showcase");
        event.setTags(Set.of("Entrepreneurship", "Startups", "Networking"));
        event.setImageUrl("https://picsum.photos/800/600?random=1003");

        assertThat(event.getId(), is(nullValue()));

        eventRepository.save(event);
        assertThat(event.getId(), is(notNullValue()));

        Optional<Event> savedEvent = eventRepository.findById(event.getId());
        assertThat(savedEvent.isPresent(), is(true));
    }

    @Test
    void testGet() {
        var result = eventRepository.findById(1L);
        assertThat(result.isPresent(), is(true));
        checkEvent(result.get());
    }

    void checkEvent(Event event) {
        assertThat(event.getTitle(), is("Test title"));
        assertThat(event.getSummary(), is("An exciting showcase where Harvard entrepreneurs and innovators present " +
                                          "their startup ideas."));
        assertThat(event.getDescription(), is("This annual event brings together the Harvard community to witness and support the entrepreneurial ideas of Harvard's brightest minds. Innovators pitch their projects to potential investors, gaining invaluable feedback and visibility. From tech startups to social enterprises, attendees will see a diverse array of ventures aimed at addressing real-world issues. It’s also a great networking opportunity for anyone interested in entrepreneurship and innovation."));
        assertThat(event.getStartDateTime().getDate(), is(LocalDate.of(2024, 11, 30)));
        assertThat(event.getStartDateTime().getTime(),
                   is(OffsetTime.of(LocalTime.of(18, 0, 0), ZoneOffset.ofHours(-5))));
        assertThat(event.getEndDateTime().getDate(), is(LocalDate.of(2024, 11, 30)));
        assertThat(event.getEndDateTime().getTime(),
                   is(OffsetTime.of(LocalTime.of(20, 30, 0), ZoneOffset.ofHours(-5))));
        assertThat(event.getHost(), is("Harvard Innovation Labs"));
//        assertThat(event.getLocation(), is("Harvard Innovation Labs, Allston, MA"));
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
}
