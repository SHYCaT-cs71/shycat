package fyi.shycat.site.entities;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Embeddable
public class DateTime {

    private LocalDate date;

    private LocalTime time;

    public DateTime() {
    }

    public DateTime(LocalDate date) {
        this.date = date;
    }

    public DateTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void addDays(int days) {
        date = date.plusDays(days);
    }

    public void addHours(int hours) {
        time = time.plusHours(hours);
    }

    @Override
    public String toString() {
        return date.toString() + (time != null ? "T" + time.format(DateTimeFormatter.ISO_LOCAL_TIME) : "");
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
