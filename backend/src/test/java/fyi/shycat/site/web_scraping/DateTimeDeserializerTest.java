package fyi.shycat.site.web_scraping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fyi.shycat.site.entities.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@JsonTest
class DateTimeDeserializerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deserializeDateOnly() throws Exception {
        var container = objectMapper.readValue("{\"dateTime\":\"2024-11-25\"}", DateTimeContainer.class);
        assertThat(container.dateTime.getDate(), is(LocalDate.of(2024, 11, 25)));
        assertThat(container.dateTime.getTime(), is(nullValue()));
    }

    @Test
    void deserializeDateTime() throws Exception {
        var container = objectMapper.readValue("{\"dateTime\":\"2024-11-25T09:30:55-05:00\"}", DateTimeContainer.class);
        assertThat(container.dateTime.getDate(), is(LocalDate.of(2024, 11, 25)));
        assertThat(container.dateTime.getTime(), is(LocalTime.of(9, 30, 55)));
    }
}

/**
 * Helper class added to test DateTime deserialization
 */
class DateTimeContainer {

    @JsonDeserialize(using = DateTimeDeserializer.class)
    DateTime dateTime;
}

