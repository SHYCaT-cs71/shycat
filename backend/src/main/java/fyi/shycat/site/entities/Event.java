package fyi.shycat.site.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "originalId")
    private String originalId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "summary", length = 500)
    private String summary;

    @Column(name = "description", length = 4000)
    private String description;

    @JsonIgnore
    @AttributeOverrides({
            @AttributeOverride(name = "date", column = @Column(name = "start_date", nullable = false)),
            @AttributeOverride(name = "time", column = @Column(name = "start_time"))
    })
    DateTime startDateTime;

    @JsonIgnore
    @AttributeOverrides({
            @AttributeOverride(name = "date", column = @Column(name = "end_date")),
            @AttributeOverride(name = "time", column = @Column(name = "end_time"))
    })
    DateTime endDateTime;

    @JsonUnwrapped
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "type", column = @Column(name = "location_type")),
            @AttributeOverride(name = "name", column = @Column(name = "location_name")),
            @AttributeOverride(name = "address", column = @Column(name = "address", length = 500)),
            @AttributeOverride(name = "locationUrl", column = @Column(name = "location_url", length = 500)),
            @AttributeOverride(name = "geo.type", column = @Column(name = "geo_type")),
            @AttributeOverride(name = "geo.latitude", column = @Column(name = "latitude")),
            @AttributeOverride(name = "geo.longitude", column = @Column(name = "longitude"))
    })
    private Location location;

    private String host;

    private String originalLink;

    private String imageUrl;

    @ElementCollection
    private Set<String> tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonGetter
    public String getStartDate() {
        return startDateTime.toString();
    }

    public DateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(DateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    @JsonGetter
    public String getEndDate() {
        return endDateTime != null ? endDateTime.toString() : null;
    }

    public DateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(DateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getOriginalLink() {
        return originalLink;
    }

    public void setOriginalLink(String originalLink) {
        this.originalLink = originalLink;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(originalId, event.originalId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(originalId);
    }
}
