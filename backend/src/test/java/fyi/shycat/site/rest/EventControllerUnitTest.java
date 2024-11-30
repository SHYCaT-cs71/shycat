package fyi.shycat.site.rest;

import fyi.shycat.site.entities.DateTime;
import fyi.shycat.site.entities.Event;
import fyi.shycat.site.repositories.EventRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = EventController.class)
class EventControllerUnitTest {

    @MockBean
    private EventRepository eventRepository;

    @Inject
    private MockMvc mvc;

    @Test
    void shouldReturnAllEvents() throws Exception {
        var events = Arrays.asList(createEvent(1L, "Event 1"), createEvent(2L, "Event 2"));
        Mockito.when(eventRepository.findAll()).thenReturn(events);

        mvc.perform(get("/events"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.*", hasSize(2)))
           .andExpect(jsonPath("$[0].title", is("Event 1")))
           .andExpect(jsonPath("$[1].title", is("Event 2")));
    }

    Event createEvent(Long id, String title) {
        var event = new Event();
        event.setId(id);
        event.setTitle(title);
        event.setStartDateTime(new DateTime(LocalDate.of(2024, 11, 27)));
        return event;
    }
}

