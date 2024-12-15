package fyi.shycat.site.repositories;

import fyi.shycat.site.entities.Event;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends ListCrudRepository<Event, Long> {

    Optional<Event> getEventByOriginalId(String originalId);

    List<Event> getEventsByStartDateTime_DateAfter(LocalDate startDate);
}
