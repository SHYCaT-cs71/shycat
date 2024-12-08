package fyi.shycat.site.web_scraping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fyi.shycat.site.entities.DateTime;
import fyi.shycat.site.entities.Event;
import fyi.shycat.site.entities.Location;
import jakarta.inject.Inject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@JsonTest
class HarvardEventWebScraperImplTest {

    private HarvardEventWebScraperImpl webScraper;
    private Document document;
    private Element eventGroup;
    private Element eventCard;

    @Inject
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        webScraper = new HarvardEventWebScraperImpl(objectMapper);

        var resource = new ClassPathResource("/sample_webpages/Harvard_2024-11-26_page2.html");
        document = Jsoup.parse(resource.getFile(), "UTF-8");
        eventGroup = webScraper.getEventGroupFromEventResults(webScraper.getEventResultsFromPage(document));
        eventCard = webScraper.getCardForEvent(eventGroup, 0);
    }

    @Test
    void getEvents_Date() throws Exception {
        // Date with 2 pages of events, 21 on page 1 and 9 on page 2
        var result = webScraper.getEvents(LocalDate.of(2024, 11, 3));
        assertThat(result, hasSize(30));
    }

    @Test
    void getEvents_Document() throws JsonProcessingException {
        var result = webScraper.getEvents(document);
        assertThat(result, hasSize(3));
    }

    @Nested
    @DisplayName("parseEvent")
    class ParseEventTests {

        @Test
        void parseEvent_Location() throws Exception {
            var eventJson = getJsonString("/sample_json/harvard_event2.json");
            var result = webScraper.parseEvent(eventCard, eventJson);
            assertThat(result, is(notNullValue()));
            // Values from the div event card
            assertThat(result.getOriginalId(), is("47117789012406"));
            assertThat(result.getTags(), containsInAnyOrder("support", "Health & Wellness"));

            // Values from the JSON object
            assertThat(result.getTitle(), is("All the World Is Here Exhibition"));
            assertThat(result.getDescription(),
                       is("Unveiled within a beautifully restored fourth-floor gallery, this exhibition features an astonishing array of over six hundred objects from Asia, Oceania, and the Americas, many on display for the very first time. Together they are woven into a compelling narrative tracing the early history of the museum’s collections and the birth of American anthropology."));
            assertThat(result.getStartDateTime(), is(new DateTime(LocalDate.of(2024, 11, 25), LocalTime.of(9, 0, 0))));
            assertThat(result.getEndDateTime(), is(new DateTime(LocalDate.of(2024, 11, 25), LocalTime.of(17, 0, 0))));
            assertThat(result.getLocation(), is(notNullValue()));
            var location = result.getLocation();
            assertThat(location.getType(), is("Place"));
            assertThat(location.getLocationUrl(), is(nullValue()));
            assertThat(location.getName(),
                       is("Peabody Museum of Archaeology \u0026 Ethnology"));
            assertThat(location.getAddress(), is("11 Divinity Avenue, Cambridge"));
            assertThat(location.getGeo(), is(notNullValue()));
            assertThat(location.getGeo().getLatitude(), is(closeTo(42.377512, 1e-8)));
            assertThat(location.getGeo().getLongitude(), is(closeTo(-71.114127, 1e-8)));
            assertThat(result.getOriginalLink(),
                       is("https://calendar.college.harvard.edu/event/all_the_world_is_here"));
            assertThat(result.getImageUrl(),
                       is("https://localist-images.azureedge.net/photos/39387743295186/huge/43c02cf08fd6ec278783408547b375692d56440b.jpg"));
        }

        @Test
        void parseEvent_NoLocation() throws Exception {
            var eventJson = getJsonString("/sample_json/harvard_event4.json");
            var eventCard = webScraper.getCardForEvent(eventGroup, 2);
            var result = webScraper.parseEvent(eventCard, eventJson);
            assertThat(result, is(notNullValue()));
            // Values from div event card
            assertThat(result.getOriginalId(), is("47605534168997"));
            assertThat(result.getTags(), contains("House Events"));

            // Values from JSON
            assertThat(result.getTitle(), is("Art Exhibition: The place where the creek goes underground"));
            assertThat(result.getDescription(),
                       is("Johnson-Kulukundis Family Gallery\nByerly Hall\n8 Garden St. \nCambridge \nMon., Nov. 25, 2024, 12 – 4:30 p.m. \n\n\n\n“The place where the creek goes underground” presents a series of newly commissioned works that form an archive of place-knowing, belonging, and kin-making. The project began with a series of conversations Anthony Romero held with brown and Indigenous artists, activists, and theorists on subjects of decolonial methodologies, gentrification, displacement, and food sovereignty. This exhibition offers an opportunity for Romero and his collaborators Deanna Ledezma and Josh Rios to create a body of work emerging from intergenerational kin-based research situated within South-Central Texas and Northern Mexico—the region the artists and their relatives call home. Through multimedia installation and life writing, they consider how familial networks maintain practices of care and transmit intimate knowledge of place shaped by the conditions of labor, immigration, marginalization, agrotourism, overdevelopment, prolonged droughts, and diminishing natural resources. This exhibition invites audiences to consider how family histories are produced and circulated within specific and interwoven sociopolitical contexts. \n\nGazette Classification: Art/Design, Diversity and Inclusion, Exhibitions \nOrganization/Sponsor: Harvard Radcliffe Institute \nCost: Free \nContact Info: events@radcliffe.harvard.edu \nMore info: www.radcliffe.harvard.edu…"));
            assertThat(result.getStartDateTime(), is(new DateTime(LocalDate.of(2024, 11, 25), LocalTime.of(12, 0, 0))));
            assertThat(result.getEndDateTime(), is(new DateTime(LocalDate.of(2024, 11, 25), null)));
            assertThat(result.getLocation(), is(nullValue()));
            assertThat(result.getOriginalLink(),
                       is("https://calendar.college.harvard.edu/event/art-exhibition-the-place-where-the-creek-goes-underground-3424"));
            assertThat(result.getImageUrl(),
                       is("https://localist-images.azureedge.net/photos/33050483441873/huge/f8c08a1d355602da94f4f659558626370fafd2e7.jpg"));
        }
    }

    @Test
    void getEventResultsFromPage() {
        var result = webScraper.getEventResultsFromPage(document);
        assertThat(result, is(notNullValue()));
        assertThat(result.id(), is("event_results"));
    }

    @Test
    void getEventGroupFromEventResults() {
        var result = webScraper.getEventGroupFromEventResults(webScraper.getEventResultsFromPage(document));
        assertThat(result, is(notNullValue()));
        assertThat(result.classNames(), hasItem("em-card-group"));
    }

    @Test
    void getNumberOfEvents() {
        var result = webScraper.getNumberOfEvents(eventGroup);
        assertThat(result, is(3));
    }

    @Test
    void getCardForEvent() {
        var result = webScraper.getCardForEvent(eventGroup, 0);
        assertThat(result, is(notNullValue()));
        assertThat(result.classNames(), hasItem("em-card"));

        var result2 = webScraper.getCardForEvent(eventGroup, 2);
        assertThat(result2, is(notNullValue()));
        assertThat(result2.classNames(), hasItem("em-card"));
    }

    @Test
    void getOriginalIdForEvent() {
        var result = webScraper.getOriginalIdForEvent(eventCard);
        assertThat(result, is(notNullValue()));
        assertThat(result, is("47117789012406"));
    }

    @Test
    void addOriginalIdToEvent() {
        var event = new Event();
        webScraper.addOriginalIdToEvent(eventCard, event);
        assertThat(event.getOriginalId(), is("47117789012406"));
    }

    @Test
    void getTagsForEvent_TwoTags() {
        var result = webScraper.getTagsForEvent(eventCard);
        assertThat(result, is(notNullValue()));
        assertThat(result, hasSize(2));
        assertThat(result, containsInAnyOrder("support", "Health & Wellness"));
    }

    @Test
    void getTagsForEvent_OneTag() {
        var result = webScraper.getTagsForEvent(eventCard);
        assertThat(result, is(notNullValue()));
        assertThat(result, hasSize(2));
        assertThat(result, containsInAnyOrder("support", "Health & Wellness"));
    }

    @Test
    void addTagsToEvent() {
        var event = new Event();
        webScraper.addTagsToEvent(eventCard, event);
        assertThat(event.getTags(), containsInAnyOrder("support", "Health & Wellness"));
    }

    @Test
    void getJsonHtmlForEvent() {
        var result = webScraper.getJsonHtmlForEvent(eventGroup, 0);
        assertThat(result, is(notNullValue()));
        assertThat(result, is("[{\"@context\":\"https://schema.org\",\"@type\":\"Event\",\"name\":\"Recovery " +
                              "Support Group for Students\",\"description\":\"Are you a Harvard student looking for ongoing support?\\n\\nThe Recovery Support Group for is a safe and confidential space to be with like–minded Harvard students seeking to gain support and strategies to help them remain substance–free while navigating the many unique and demanding  phases of being a student at Harvard. Offered virtually on Tuesday evenings, this is a warm and supportive space where students gain skills for sobriety, and support in navigating a self–directed learning environment, professional and career concerns,  as well as thriving in personal and family life .\\n\\nThere is no requirement for length of abstinence just a desire to remain free of substances.\\n\\nOpen to all registered Harvard students who have paid the 2024-2025 Student Health Fee.\\n\\nContact Janet Lawrence, LICSW directly at Jlawrence@huhs.harvard.edu \\n\\nIf you are interested, please email the group leader directly and include your Student ID number, which allows CAMHS to securely message the workshop/group zoom link.\",\"startDate\":\"2024-11-26T18:00:00-05:00\",\"endDate\":\"2024-11-26T19:15:00-05:00\",\"eventStatus\":\"EventScheduled\",\"location\":{\"@type\":\"VirtualLocation\",\"url\":\"https://calendar.college.harvard.edu/event/recovery_support_group_for_students\"},\"url\":\"https://calendar.college.harvard.edu/event/recovery_support_group_for_students\",\"image\":\"https://localist-images.azureedge.net/photos/47296246797745/huge/65d5950c50e1753ef8747a06ebf5c8617565df46.jpg\"}]"));

        var result2 = webScraper.getJsonHtmlForEvent(eventGroup, 2);
        assertThat(result2, is(notNullValue()));
        assertThat(result2, is("[{\"@context\":\"https://schema.org\",\"@type\":\"Event\",\"name\":\"Writers' Work-Throp\",\"description\":\"Come to the Writers Work-Throp to work on your personal writing projects each week! We will have yummy snacks and good company.*\",\"startDate\":\"2024-11-26T19:30:00-05:00\",\"endDate\":\"2024-11-26T20:30:00-05:00\",\"eventStatus\":\"EventScheduled\",\"location\":{\"@type\":\"Place\",\"name\":\"JCR\",\"address\":\"\"},\"url\":\"https://calendar.college.harvard.edu/event/writers-work-throp\",\"image\":\"https://localist-images.azureedge.net/photos/47605535161350/huge/b96131621ff2ed86acd58c330e15d8f4ae88e8c5.jpg\"}]"));
    }

    @Test
    void generateUrlFromDate() {
        var result = webScraper.generateUrlFromDate(LocalDate.of(2020, 3, 14), 1);
        assertThat(result, is(notNullValue()));
        assertThat(result, is("https://calendar.college.harvard.edu/calendar/day/2020/3/14/1"));
    }

    @Test
    void copyLocationJsonIntoEntity() {
        var locationJson = createLocationJson();
        var result = webScraper.copyLocationJsonIntoEntity(locationJson);
        checkLocationJsonToEntity(result);
    }

    @Test
    void copyEventJsonIntoEntity() {
        var eventJson = new LocalistEventJson();
        eventJson.setType("Type");
        eventJson.setEventStatus("EventScheduled");
        eventJson.setContext("Context");
        eventJson.setName("Event Name");
        eventJson.setDescription("Event Description is longer.");
        eventJson.setStartDate(new DateTime(LocalDate.of(2024, 12, 8), null));
        eventJson.setEndDate(new DateTime(LocalDate.of(2024, 12, 9), null));
        eventJson.setLocation(createLocationJson());
        eventJson.setOriginalLink("stringmadeupurl");
        eventJson.setImageUrl("madeupimageurl");

        var result = webScraper.copyEventJsonIntoEntity(eventJson);
        assertThat(result, is(notNullValue()));
        assertThat(result.getTitle(), is("Event Name"));
        assertThat(result.getSummary(), is(nullValue()));
        assertThat(result.getDescription(), is("Event Description is longer."));
        assertThat(result.getOriginalId(), is(nullValue()));
        assertThat(result.getStartDateTime(), is(eventJson.getStartDate()));
        assertThat(result.getEndDateTime(), is(eventJson.getEndDate()));
        assertThat(result.getHost(), is(nullValue()));
        assertThat(result.getOriginalLink(), is("stringmadeupurl"));
        assertThat(result.getImageUrl(), is("madeupimageurl"));
        assertThat(result.getTags(), is(nullValue()));
        checkLocationJsonToEntity(result.getLocation());
    }

    LocalistLocationJson createLocationJson() {
        var locationJson = new LocalistLocationJson();
        locationJson.setType("Place");
        locationJson.setName("Location Name");
        locationJson.setLocationUrl("https://location.nonsense");
        locationJson.setAddress("123 Main St");

        var geo = new LocalistLocationJson.GeoLocation(123, 456);
        geo.setType("GeoCoords");
        locationJson.setGeo(geo);
        return locationJson;
    }

    void checkLocationJsonToEntity(Location result) {
        assertThat(result, is(notNullValue()));
        assertThat(result.getType(), is("Place"));
        assertThat(result.getName(), is("Location Name"));
        assertThat(result.getLocationUrl(), is("https://location.nonsense"));
        assertThat(result.getAddress(), is("123 Main St"));
        assertThat(result.getGeo(), is(notNullValue()));
        var resultGeo = result.getGeo();
        assertThat(resultGeo.getType(), is("GeoCoords"));
        assertThat(resultGeo.getLatitude(), is(123.0));
        assertThat(resultGeo.getLongitude(), is(456.0));
    }

    @DisplayName("getEventJson")
    @Nested
    class GetEventJsonTests {

        @Test
        void getEventJson_Virtual_DateTime() throws Exception {
            var jsonHtml = webScraper.getJsonHtmlForEvent(eventGroup, 0);
            var eventJson = webScraper.getEventJson(jsonHtml);
            assertThat(eventJson, is(notNullValue()));
            assertThat(eventJson.getContext(), is("https://schema.org"));
            assertThat(eventJson.getType(), is("Event"));
            assertThat(eventJson.getName(), is("Recovery Support Group for Students"));
            assertThat(eventJson.getDescription(),
                       is("Are you a Harvard student looking for ongoing support?\n\nThe Recovery Support Group for is a safe and confidential space to be with like–minded Harvard students seeking to gain support and strategies to help them remain substance–free while navigating the many unique and demanding  phases of being a student at Harvard. Offered virtually on Tuesday evenings, this is a warm and supportive space where students gain skills for sobriety, and support in navigating a self–directed learning environment, professional and career concerns,  as well as thriving in personal and family life .\n\nThere is no requirement for length of abstinence just a desire to remain free of substances.\n\nOpen to all registered Harvard students who have paid the 2024-2025 Student Health Fee.\n\nContact Janet Lawrence, LICSW directly at Jlawrence@huhs.harvard.edu \n\nIf you are interested, please email the group leader directly and include your Student ID number, which allows CAMHS to securely message the workshop/group zoom link."));
            assertThat(eventJson.getStartDate(), is(new DateTime(LocalDate.of(2024, 11, 26), LocalTime.of(18, 0, 0))));
            assertThat(eventJson.getEndDate(), is(new DateTime(LocalDate.of(2024, 11, 26), LocalTime.of(19, 15, 0))));
            assertThat(eventJson.getEventStatus(), is("EventScheduled"));
            assertThat(eventJson.getLocation(), is(notNullValue()));
            var location = eventJson.getLocation();
            assertThat(location.getType(), is("VirtualLocation"));
            assertThat(location.getLocationUrl(),
                       is("https://calendar.college.harvard.edu/event/recovery_support_group_for_students"));
            assertThat(location.getGeo(), is(nullValue()));
            assertThat(location.getName(), is(nullValue()));
            assertThat(location.getAddress(), is(nullValue()));
            assertThat(eventJson.getOriginalLink(),
                       is("https://calendar.college.harvard.edu/event/recovery_support_group_for_students"));
            assertThat(eventJson.getImageUrl(),
                       is("https://localist-images.azureedge.net/photos/47296246797745/huge/65d5950c50e1753ef8747a06ebf5c8617565df46.jpg"));
        }

        @Test
        void getEventJson_LocationNoAddress_DateOnly() throws Exception {
            var eventJson = getEventJson("/sample_json/harvard_event1.json");
            assertThat(eventJson, is(notNullValue()));
            assertThat(eventJson.getContext(), is("https://schema.org"));
            assertThat(eventJson.getType(), is("Event"));
            assertThat(eventJson.getName(), is("Harvard Public Art \u0026 Culture Tour"));
            assertThat(eventJson.getDescription(),
                       is("Discover a new world of public art in and around Allston and Cambridge! Choose a self-guided tour and learn the captivating stories behind a variety of artworks and their artists. You’ll explore big names in art and architecture, thought-provoking contemporary installations, longstanding cultural institutions—and be amazed as hidden gems reveal themselves in plain sight!"));
            assertThat(eventJson.getStartDate(), is(new DateTime(LocalDate.of(2024, 11, 25), null)));
            assertThat(eventJson.getEndDate(), is(new DateTime(LocalDate.of(2024, 11, 25), null)));
            assertThat(eventJson.getEventStatus(), is("EventScheduled"));
            assertThat(eventJson.getLocation(), is(notNullValue()));
            var location = eventJson.getLocation();
            assertThat(location.getType(), is("Place"));
            assertThat(location.getLocationUrl(), is(nullValue()));
            assertThat(location.getName(),
                       is("Multiple locations in Harvard Square, Cambridge and along Western Avenue, Allston"));
            assertThat(location.getAddress(), is(""));
            assertThat(location.getGeo(), is(nullValue()));
            assertThat(eventJson.getOriginalLink(),
                       is("https://calendar.college.harvard.edu/event/harvard_public_art_culture_tour"));
            assertThat(eventJson.getImageUrl(),
                       is("https://localist-images.azureedge.net/photos/44259536818152/huge/d3af645d154476c36e058cb67d51d154ae4f2206.jpg"));
        }

        @Test
        void getEventJson_LocationWithAddress_DateTime() throws Exception {
            var eventJson = getEventJson("/sample_json/harvard_event2.json");
            assertThat(eventJson, is(notNullValue()));
            assertThat(eventJson.getContext(), is("https://schema.org"));
            assertThat(eventJson.getType(), is("Event"));
            assertThat(eventJson.getName(), is("All the World Is Here Exhibition"));
            assertThat(eventJson.getDescription(),
                       is("Unveiled within a beautifully restored fourth-floor gallery, this exhibition features an astonishing array of over six hundred objects from Asia, Oceania, and the Americas, many on display for the very first time. Together they are woven into a compelling narrative tracing the early history of the museum’s collections and the birth of American anthropology."));
            assertThat(eventJson.getStartDate(), is(new DateTime(LocalDate.of(2024, 11, 25), LocalTime.of(9, 0, 0))));
            assertThat(eventJson.getEndDate(), is(new DateTime(LocalDate.of(2024, 11, 25), LocalTime.of(17, 0, 0))));
            assertThat(eventJson.getEventStatus(), is("EventScheduled"));
            assertThat(eventJson.getLocation(), is(notNullValue()));
            var location = eventJson.getLocation();
            assertThat(location.getType(), is("Place"));
            assertThat(location.getLocationUrl(), is(nullValue()));
            assertThat(location.getName(),
                       is("Peabody Museum of Archaeology \u0026 Ethnology"));
            assertThat(location.getAddress(), is("11 Divinity Avenue, Cambridge"));
            assertThat(location.getGeo(), is(notNullValue()));
            assertThat(location.getGeo().getLatitude(), is(closeTo(42.377512, 1e-8)));
            assertThat(location.getGeo().getLongitude(), is(closeTo(-71.114127, 1e-8)));
            assertThat(eventJson.getOriginalLink(),
                       is("https://calendar.college.harvard.edu/event/all_the_world_is_here"));
            assertThat(eventJson.getImageUrl(),
                       is("https://localist-images.azureedge.net/photos/39387743295186/huge/43c02cf08fd6ec278783408547b375692d56440b.jpg"));
        }

        @Test
        void getEventJson_NoLocation_StartDateTime() throws Exception {
            // Event has time for the startDate but not for the endDate
            var eventJson = getEventJson("/sample_json/harvard_event4.json");
            assertThat(eventJson, is(notNullValue()));
            assertThat(eventJson.getContext(), is("https://schema.org"));
            assertThat(eventJson.getType(), is("Event"));
            assertThat(eventJson.getName(), is("Art Exhibition: The place where the creek goes underground"));
            assertThat(eventJson.getDescription(),
                       is("Johnson-Kulukundis Family Gallery\nByerly Hall\n8 Garden St. \nCambridge \nMon., Nov. 25, 2024, 12 – 4:30 p.m. \n\n\n\n“The place where the creek goes underground” presents a series of newly commissioned works that form an archive of place-knowing, belonging, and kin-making. The project began with a series of conversations Anthony Romero held with brown and Indigenous artists, activists, and theorists on subjects of decolonial methodologies, gentrification, displacement, and food sovereignty. This exhibition offers an opportunity for Romero and his collaborators Deanna Ledezma and Josh Rios to create a body of work emerging from intergenerational kin-based research situated within South-Central Texas and Northern Mexico—the region the artists and their relatives call home. Through multimedia installation and life writing, they consider how familial networks maintain practices of care and transmit intimate knowledge of place shaped by the conditions of labor, immigration, marginalization, agrotourism, overdevelopment, prolonged droughts, and diminishing natural resources. This exhibition invites audiences to consider how family histories are produced and circulated within specific and interwoven sociopolitical contexts. \n\nGazette Classification: Art/Design, Diversity and Inclusion, Exhibitions \nOrganization/Sponsor: Harvard Radcliffe Institute \nCost: Free \nContact Info: events@radcliffe.harvard.edu \nMore info: www.radcliffe.harvard.edu…"));
            assertThat(eventJson.getStartDate(), is(new DateTime(LocalDate.of(2024, 11, 25), LocalTime.of(12, 0, 0))));
            assertThat(eventJson.getEndDate(), is(new DateTime(LocalDate.of(2024, 11, 25), null)));
            assertThat(eventJson.getEventStatus(), is("EventScheduled"));
            assertThat(eventJson.getLocation(), is(nullValue()));
            assertThat(eventJson.getOriginalLink(),
                       is("https://calendar.college.harvard.edu/event/art-exhibition-the-place-where-the-creek-goes-underground-3424"));
            assertThat(eventJson.getImageUrl(),
                       is("https://localist-images.azureedge.net/photos/33050483441873/huge/f8c08a1d355602da94f4f659558626370fafd2e7.jpg"));
        }

        LocalistEventJson getEventJson(String filePath) throws Exception {
            var jsonHtml = getJsonString(filePath);
            return webScraper.getEventJson(jsonHtml);
        }
    }

    String getJsonString(String filePath) throws Exception {
        var resource = new ClassPathResource(filePath);
        return resource.getContentAsString(Charset.defaultCharset());
    }
}
