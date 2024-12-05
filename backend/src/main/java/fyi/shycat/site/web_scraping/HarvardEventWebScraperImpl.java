package fyi.shycat.site.web_scraping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fyi.shycat.site.entities.Event;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
    public List<Event> getEvents(LocalDate date) {
        return List.of();
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
        return eventGroup.select(".em-card").get(index * 2);
    }

    String getOriginalIdForEvent(Element event) {
        // em-event-instance-47117789012406
        String className =
                event.classNames().stream().filter(name -> name.startsWith("em-event-instance-"))
                     .findFirst().get();
        return className.replace("em-event-instance-", "");
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

    String getJsonHtmlForEvent(Element eventGroup, int index) {
        return eventGroup.select("script").get(index * 2).html();
    }

    LocalistEventJson getEventJson(String jsonHtml) throws JsonProcessingException {
        var eventJson = objectMapper.readValue(jsonHtml, LocalistEventJson[].class);
        return eventJson[0];
    }
}
