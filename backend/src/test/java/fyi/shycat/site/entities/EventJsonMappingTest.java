package fyi.shycat.site.entities;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class EventJsonMappingTest {

    @Inject
    private JacksonTester<Event> jacksonTester;

    @Test
    void testEventSerialization_FullProperties() throws Exception {
        // Test serialization of Event with values for all properties
        var event = createBaseEvent(1L);
        event.setStartDateTime(new DateTime(LocalDate.of(2024, 11, 27),
                                            LocalTime.of(20, 5, 15)));
        event.setEndDateTime(new DateTime(LocalDate.of(2024, 11, 28),
                                          LocalTime.of(10, 36, 44)));
        var location = new Location();
        location.setName("Location name");
        location.setAddress("123 Main St, Cambridge");
        location.setGeo(new Location.GeoLocation(41.3, -57.8));
        event.setLocation(location);

        var json = jacksonTester.write(event);
        checkBaseEvent(json, 1);
        assertThat(json).extractingJsonPathStringValue("$.startDate").isEqualTo("2024-11-27T20:05:15");
        assertThat(json).extractingJsonPathStringValue("$.endDate").isEqualTo("2024-11-28T10:36:44");
        assertThat(json).extractingJsonPathStringValue("$.locationName").isEqualTo("Location name");
        assertThat(json).extractingJsonPathStringValue("$.locationAddress").isEqualTo("123 Main St, Cambridge");
        assertThat(json).extractingJsonPathNumberValue("$.locationGeo.latitude").isEqualTo(41.3);
        assertThat(json).extractingJsonPathNumberValue("$.locationGeo.longitude").isEqualTo(-57.8);
    }

    @Test
    void testEventSerialization_DateOnly_LocationNameOnly() throws Exception {
        // Test serialization of Event with only dates (no times) and only location name
        var event = createBaseEvent(2L);
        event.setStartDateTime(new DateTime(LocalDate.of(2024, 11, 27), null));
        event.setEndDateTime(new DateTime(LocalDate.of(2024, 11, 28), null));
        var location = new Location();
        location.setName("Location name");
        event.setLocation(location);

        var json = jacksonTester.write(event);
        checkBaseEvent(json, 2);
        assertThat(json).extractingJsonPathStringValue("$.startDate").isEqualTo("2024-11-27");
        assertThat(json).extractingJsonPathStringValue("$.endDate").isEqualTo("2024-11-28");
        assertThat(json).extractingJsonPathStringValue("$.locationName").isEqualTo("Location name");
        assertThat(json).hasEmptyJsonPathValue("$.locationAddress");
        assertThat(json).hasEmptyJsonPathValue("$.locationGeo");
    }

    @Test
    void testEventSerialization_NoLocation_NoEndDate() throws Exception {
        // Test serialization of Event with no location or end date
        var event = createBaseEvent(3L);
        event.setStartDateTime(new DateTime(LocalDate.of(2024, 11, 27), null));

        var json = jacksonTester.write(event);
        checkBaseEvent(json, 3);
        assertThat(json).extractingJsonPathStringValue("$.startDate").isEqualTo("2024-11-27");
        assertThat(json).hasEmptyJsonPathValue("$.endDate");
        assertThat(json).hasEmptyJsonPathValue("$.locationName");
        assertThat(json).hasEmptyJsonPathValue("$.locationAddress");
        assertThat(json).hasEmptyJsonPathValue("$.locationGeo");
    }

    Event createBaseEvent(long id) {
        var event = new Event();
        event.setId(id);
        event.setTitle("Event Title");
        event.setSummary("Event has a slightly longer summary.");
        event.setDescription("Event description should be even longer than the summary.");
        event.setHost("Event host");
        event.setOriginalLink("https://www.nonsense.com/original_link");
        event.setImageUrl("https://www.nonsense.com/image_url");
        event.setTags(Set.of("Tag A", "Tag B"));
        return event;
    }

    void checkBaseEvent(JsonContent<Event> json, int id) {
        assertThat(json).extractingJsonPathNumberValue("$.id").isEqualTo(id);
        assertThat(json).extractingJsonPathStringValue("$.title").isEqualTo("Event Title");
        assertThat(json).extractingJsonPathStringValue("$.summary")
                        .isEqualTo("Event has a slightly longer summary.");
        assertThat(json).extractingJsonPathStringValue("$.description")
                        .isEqualTo("Event description should be even longer than the summary.");
        assertThat(json).extractingJsonPathStringValue("$.host").isEqualTo("Event host");
        assertThat(json).extractingJsonPathStringValue("$.originalLink").isEqualTo("https://www.nonsense.com/original_link");
        assertThat(json).extractingJsonPathStringValue("$.imageUrl").isEqualTo("https://www.nonsense.com/image_url");
        assertThat(json).extractingJsonPathArrayValue("$.tags").containsExactlyInAnyOrder("Tag A", "Tag B");
    }
}
