package fyi.shycat.site.rest;

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
import java.util.Optional;

import static fyi.shycat.site.repositories.EventRepositoryTest.createEvent;
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
        Mockito.when(eventRepository.getEventsByStartDateTime_DateAfter(Mockito.any(LocalDate.class))).thenReturn(events);

        mvc.perform(get("/events"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.*", hasSize(2)))
           .andExpect(jsonPath("$[0].title", is("Event 1")))
           .andExpect(jsonPath("$[1].title", is("Event 2")));
    }

    @Test
    void shouldReturnEventById() throws Exception {
        var event = createEvent(1L, "Event 1");
        Mockito.when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
        mvc.perform(get("/events/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Event 1")));
    }
}

