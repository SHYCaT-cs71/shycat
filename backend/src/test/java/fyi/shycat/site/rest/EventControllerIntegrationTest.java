package fyi.shycat.site.rest;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerIntegrationTest {

    @Inject
    private MockMvc mvc;

    @Test
    void testGetAll() throws Exception {
        mvc.perform(get("/events").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)));
    }

    @Test
    void testGetEventById_AllFilled() throws Exception {
        mvc.perform(get("/events/1").contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.id", is(1)))
           .andExpect(jsonPath("$.originalId", is("10001")))
           .andExpect(jsonPath("$.title", is("Test title")))
           .andExpect(jsonPath("$.summary",
                               is("An exciting showcase where Harvard entrepreneurs and innovators present their startup ideas.")))
           .andExpect(jsonPath("$.description",
                               is("This annual event brings together the Harvard community to witness and support the entrepreneurial ideas of Harvard's brightest minds. Innovators pitch their projects to potential investors, gaining invaluable feedback and visibility. From tech startups to social enterprises, attendees will see a diverse array of ventures aimed at addressing real-world issues. It’s also a great networking opportunity for anyone interested in entrepreneurship and innovation.")))
           .andExpect(jsonPath("$.startDate", is("2024-11-30T18:00:00")))
           .andExpect(jsonPath("$.endDate", is("2024-11-30T20:30:00")))
           .andExpect(jsonPath("$.locationType", is("Place")))
           .andExpect(jsonPath("$.locationName", is("Harvard i-lab, Batten Hall")))
           .andExpect(jsonPath("$.locationUrl", is("https://nonsense.com")))
           .andExpect(jsonPath("$.locationAddress", is("125 Western Ave, Allston")))
           .andExpect(jsonPath("$.locationGeo.latitude", is(42.36405444183414)))
           .andExpect(jsonPath("$.locationGeo.longitude", is(-71.12418004499675)))
           .andExpect(jsonPath("$.host", is("Harvard Innovation Labs")))
           .andExpect(jsonPath("$.originalLink", is("https://example.com/harvard-startup-showcase")))
           .andExpect(jsonPath("$.imageUrl", is("https://picsum.photos/800/600?random=0.1")))
                .andExpect(jsonPath("$.tags", hasSize(3)))
                .andExpect(jsonPath("$.tags", containsInAnyOrder("Entrepreneurship", "Startups", "Networking")));
    }

    @Test
    void testGetEventById_Empty() throws Exception {
        mvc.perform(get("/events/5").contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.id", is(5)))
           .andExpect(jsonPath("$.originalId", is("10005")))
           .andExpect(jsonPath("$.title", is("Harvard Environmental Conference")))
           .andExpect(jsonPath("$.summary").doesNotExist())
           .andExpect(jsonPath("$.description",
                               is("Join experts, students, and community leaders to discuss the latest research and solutions for environmental sustainability. Sessions will cover diverse topics, including climate action, renewable energy, and policy advocacy. The conference provides a platform for impactful discussions,\n" +
                                  "with workshops designed to inspire actionable solutions to pressing environmental issues.")))
           .andExpect(jsonPath("$.startDate", is("2025-02-20")))
           .andExpect(jsonPath("$.endDate", is("2025-02-22")))
           .andExpect(jsonPath("$.locationType").doesNotExist())
           .andExpect(jsonPath("$.locationName").doesNotExist())
           .andExpect(jsonPath("$.locationUrl").doesNotExist())
           .andExpect(jsonPath("$.locationAddress").doesNotExist())
           .andExpect(jsonPath("$.locationGeo.latitude").doesNotExist())
           .andExpect(jsonPath("$.locationGeo.longitude").doesNotExist())
           .andExpect(jsonPath("$.host").doesNotExist())
           .andExpect(jsonPath("$.originalLink", is("https://example.com/harvard-environmental-conference")))
           .andExpect(jsonPath("$.imageUrl", is("https://picsum.photos/800/600?random=0.5")))
           .andExpect(jsonPath("$.tags", is(empty())));
    }
}
