package fyi.shycat.site.web_scraping;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import fyi.shycat.site.entities.DateTime;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public class DateTimeDeserializer extends JsonDeserializer<DateTime> {

    public DateTimeDeserializer() {
        super();
    }

    @Override
    public DateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JacksonException {
        var dateTime = new DateTime();
        var stringDate = jsonParser.getText();
        if (stringDate.contains("T")) {
            var offsetDateTime = OffsetDateTime.parse(stringDate);
            dateTime.setDate(offsetDateTime.toLocalDate());
            dateTime.setTime(offsetDateTime.toLocalTime());
        }
        else {
            var date = jsonParser.readValueAs(LocalDate.class);
            dateTime.setDate(date);
        }
        return dateTime;
    }
}
