package fyi.shycat.site.entities;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Embeddable
public class DateTime {

    private LocalDate date;

    private OffsetTime time;

    public DateTime() {
    }

    public DateTime(LocalDate date, OffsetTime time) {
        this.date = date;
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public OffsetTime getTime() {
        return time;
    }

    public void setTime(OffsetTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return date.toString() + (time != null ? " " + time.format(DateTimeFormatter.ISO_OFFSET_TIME) : "");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DateTime dateTime = (DateTime) o;
        return Objects.equals(date, dateTime.date) && Objects.equals(time, dateTime.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time);
    }
}
