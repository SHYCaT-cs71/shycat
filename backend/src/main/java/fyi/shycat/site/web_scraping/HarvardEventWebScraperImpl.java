package fyi.shycat.site.web_scraping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fyi.shycat.site.entities.Event;
import fyi.shycat.site.entities.Location;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class HarvardEventWebScraperImpl implements WebScraper {

    private final ObjectMapper objectMapper;

    @Autowired
    public HarvardEventWebScraperImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Event> getEvents(LocalDate date) throws IOException {
        var events = new ArrayList<Event>();
        var page = 1;
        var document = Jsoup.connect(generateUrlFromDate(date, page)).get();
        var pageEvents = getEvents(document);
        while (!pageEvents.isEmpty()) {
            events.addAll(pageEvents);
            page++;
            document = Jsoup.connect(generateUrlFromDate(date, page)).get();
            pageEvents = getEvents(document);
        }
        return events;
    }

    List<Event> getEvents(Document document) throws JsonProcessingException {
        var list = new ArrayList<Event>();
        var eventResult = getEventResultsFromPage(document);
        var eventGroup = getEventGroupFromEventResults(eventResult);
        if (eventGroup != null) {
            var numberOfEvents = getNumberOfEvents(eventGroup);
            for (int i = 0; i < numberOfEvents; i++) {
                var eventCard = getCardForEvent(eventGroup, i);
                var eventJsonHtml = getJsonHtmlForEvent(eventGroup, i);
                var event = parseEvent(eventCard, eventJsonHtml);
                list.add(event);
            }
        }
        return list;
    }

    Event parseEvent(Element eventCard, String eventJsonHTML) throws JsonProcessingException {
        var eventJson = getEventJson(eventJsonHTML);
        var event = copyEventJsonIntoEntity(eventJson);
        addOriginalIdToEvent(eventCard, event);
        addTagsToEvent(eventCard, event);
        return event;
    }

    String generateUrlFromDate(LocalDate date, int page) {
        return "https://calendar.college.harvard.edu/calendar/day/" + date.getYear() + "/" + date.getMonthValue()
               + "/" + date.getDayOfMonth() + "/" + page;
    }

    Element getEventResultsFromPage(Document document) {
        // There's only one element with id "event_results" - since it's by id, don't even need to bother getting the
        // container parent first
        return document.selectFirst("#event_results");
    }

    Element getEventGroupFromEventResults(Element eventResults) {
        // The event_results parent can contain multiple ".em-content_label" headers, one for each date of listed
        // events, each immediately followed by a ".em-card-group" element holding the event cards for that date
        // Each ".em-card-group" has a bunch of elements, 2 per event card
        return eventResults.selectFirst(".em-card-group");
    }

    int getNumberOfEvents(Element eventGroup) {
        return eventGroup.childrenSize() / 2;
    }

    Element getCardForEvent(Element eventGroup, int index) {
        return eventGroup.select(".em-card").get(index);
    }

    String getOriginalIdForEvent(Element event) {
        // em-event-instance-47117789012406
        String className =
                event.classNames().stream().filter(name -> name.startsWith("em-event-instance-"))
                     .findFirst().get();
        return className.replace("em-event-instance-", "");
    }

    void addOriginalIdToEvent(Element eventCard, Event event) {
        var originalId = getOriginalIdForEvent(eventCard);
        event.setOriginalId(originalId);
    }

    Set<String> getTagsForEvent(Element event) {
        var tags = new HashSet<String>();

        // <div class="tag">
        var tag = event.select(".tag");
        if (!tag.isEmpty()) {
            var tagValue = tag.select("a").first().text();
            tags.add(tagValue);
        }

        // <div class="em-list_tags">
        //    <a class="em-card_tag">Health &amp; Wellness</a>
        var tagList = event.select(".em-card_tag");
        for (Element tagElement : tagList) {
            tags.add(tagElement.text());
        }

        return tags;
    }

    void addTagsToEvent(Element eventCard, Event event) {
        var tags = getTagsForEvent(eventCard);
        event.setTags(tags);
    }

    String getJsonHtmlForEvent(Element eventGroup, int index) {
        return eventGroup.select("script").get(index).html();
    }

    LocalistEventJson getEventJson(String jsonHtml) throws JsonProcessingException {
        var eventJson = objectMapper.readValue(jsonHtml, LocalistEventJson[].class);
        return eventJson[0];
    }

    Location copyLocationJsonIntoEntity(LocalistLocationJson locationJson) {
        var location = new Location();
        location.setType(locationJson.getType());
        location.setName(locationJson.getName());
        location.setLocationUrl(locationJson.getLocationUrl());
        location.setAddress(locationJson.getAddress());
        if (locationJson.getGeo() != null) {
            var geoJson = locationJson.getGeo();
            var geo = new Location.GeoLocation();
            geo.setType(geoJson.getType());
            geo.setLatitude(geoJson.getLatitude());
            geo.setLongitude(geoJson.getLongitude());
            location.setGeo(geo);
        }
        return location;
    }

    Event copyEventJsonIntoEntity(LocalistEventJson eventJson) {
        var event = new Event();
        event.setTitle(eventJson.getName());
        event.setDescription(eventJson.getDescription());
        event.setStartDateTime(eventJson.getStartDate());
        event.setEndDateTime(eventJson.getEndDate());
        event.setOriginalLink(eventJson.getOriginalLink());
        event.setImageUrl(eventJson.getImageUrl());
        if (eventJson.getLocation() != null) {
            event.setLocation(copyLocationJsonIntoEntity(eventJson.getLocation()));
        }
        return event;
    }

}
