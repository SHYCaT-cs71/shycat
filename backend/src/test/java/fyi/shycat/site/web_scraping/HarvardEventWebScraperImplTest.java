package fyi.shycat.site.web_scraping;

import com.fasterxml.jackson.databind.ObjectMapper;
import fyi.shycat.site.entities.DateTime;
import jakarta.inject.Inject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
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
    }

    @Test
    void getOriginalIdForEvent() {
        var result = webScraper.getOriginalIdForEvent(eventCard);
        assertThat(result, is(notNullValue()));
        assertThat(result, is("47117789012406"));
    }

    @Test
    void getTagsForEvent() {
        var result = webScraper.getTagsForEvent(eventCard);
        assertThat(result, is(notNullValue()));
        assertThat(result, hasSize(2));
        assertThat(result, containsInAnyOrder("support", "Health & Wellness"));
    }

    @Test
    void getJsonHtmlForEvent() {
        var result = webScraper.getJsonHtmlForEvent(eventGroup, 0);
        assertThat(result, is(notNullValue()));
        assertThat(result, is("[{\"@context\":\"https://schema.org\",\"@type\":\"Event\",\"name\":\"Recovery " +
                              "Support Group for Students\",\"description\":\"Are you a Harvard student looking for ongoing support?\\n\\nThe Recovery Support Group for is a safe and confidential space to be with like–minded Harvard students seeking to gain support and strategies to help them remain substance–free while navigating the many unique and demanding  phases of being a student at Harvard. Offered virtually on Tuesday evenings, this is a warm and supportive space where students gain skills for sobriety, and support in navigating a self–directed learning environment, professional and career concerns,  as well as thriving in personal and family life .\\n\\nThere is no requirement for length of abstinence just a desire to remain free of substances.\\n\\nOpen to all registered Harvard students who have paid the 2024-2025 Student Health Fee.\\n\\nContact Janet Lawrence, LICSW directly at Jlawrence@huhs.harvard.edu \\n\\nIf you are interested, please email the group leader directly and include your Student ID number, which allows CAMHS to securely message the workshop/group zoom link.\",\"startDate\":\"2024-11-26T18:00:00-05:00\",\"endDate\":\"2024-11-26T19:15:00-05:00\",\"eventStatus\":\"EventScheduled\",\"location\":{\"@type\":\"VirtualLocation\",\"url\":\"https://calendar.college.harvard.edu/event/recovery_support_group_for_students\"},\"url\":\"https://calendar.college.harvard.edu/event/recovery_support_group_for_students\",\"image\":\"https://localist-images.azureedge.net/photos/47296246797745/huge/65d5950c50e1753ef8747a06ebf5c8617565df46.jpg\"}]"));
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
            var resource = new ClassPathResource(filePath);
            var jsonHtml = resource.getContentAsString(Charset.defaultCharset());
            return webScraper.getEventJson(jsonHtml);
        }
    }
}
