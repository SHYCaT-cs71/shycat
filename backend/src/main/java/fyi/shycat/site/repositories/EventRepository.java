package fyi.shycat.site.repositories;

import fyi.shycat.site.entities.Event;
import org.springframework.data.repository.ListCrudRepository;

public interface EventRepository extends ListCrudRepository<Event, Long> {
}
