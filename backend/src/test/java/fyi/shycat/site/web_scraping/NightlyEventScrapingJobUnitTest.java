package fyi.shycat.site.web_scraping;

import fyi.shycat.site.entities.DateTime;
import fyi.shycat.site.entities.Event;
import fyi.shycat.site.repositories.EventRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static fyi.shycat.site.repositories.EventRepositoryTest.createEvent;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;

@ExtendWith(MockitoExtension.class)
class NightlyEventScrapingJobUnitTest {

    private Event newEvent;
    private Event savedEvent;
    private NightlyEventScrapingJob job;

    @Mock
    private HarvardEventWebScraperImpl harvardEventWebScraper;

    @Mock
    private EventRepository eventRepository;

    @BeforeEach
    void setUp() {
        newEvent = createEvent(null, "Test Event");
        savedEvent = createEvent(1L, "Test Event");
        job = new NightlyEventScrapingJob(harvardEventWebScraper, eventRepository);
    }

    @AfterEach
    void checkMocks() {
        Mockito.verifyNoMoreInteractions(harvardEventWebScraper, eventRepository);
    }

    @Test
    void scrapeHarvardEvents_LocalDate_LocalDate() throws Exception {
        var newEvent2 = createEvent(null, "Test Event2");
        newEvent2.setOriginalId("606060");

        Mockito.when(harvardEventWebScraper.getEvents(LocalDate.of(2024, 11, 3)))
                .thenReturn(List.of(newEvent));
        Mockito.when(harvardEventWebScraper.getEvents(LocalDate.of(2024, 11, 4)))
                .thenReturn(List.of(newEvent2));

        Mockito.when(eventRepository.getEventByOriginalId(newEvent.getOriginalId()))
               .thenReturn(Optional.of(savedEvent));
        Mockito.when(eventRepository.getEventByOriginalId(newEvent2.getOriginalId())).thenReturn(Optional.empty());

        var result = job.scrapeHarvardEvents(LocalDate.of(2024, 11, 3), LocalDate.of(2024, 11, 5));

        assertThat(result, is(2));
        Mockito.verify(eventRepository).getEventByOriginalId(newEvent.getOriginalId());
        Mockito.verify(eventRepository).getEventByOriginalId(newEvent2.getOriginalId());
        Mockito.verify(eventRepository, Mockito.times(0)).save(newEvent);
        Mockito.verify(eventRepository).save(newEvent2);
    }

    @Nested
    @DisplayName("handleScrapedEvent")
    class HandleScrapedEventTests {

        @AfterEach
        void checkMocks() {
            Mockito.verify(eventRepository).getEventByOriginalId(newEvent.getOriginalId());
        }

        @Test
        void handleScrapedEvent_Unsaved() {
            Mockito.when(eventRepository.getEventByOriginalId(newEvent.getOriginalId()))
                   .thenReturn(Optional.empty());

            newEvent.setTitle("New Title");
            newEvent.getStartDateTime().addDays(1);

            job.handleScrapedEvent(newEvent);
            // Check that savedEvent values haven't changed, since it wasn't updated
            assertThat(savedEvent.getTitle(), is("Test Event"));
            assertThat(savedEvent.getStartDateTime(), is(new DateTime(LocalDate.of(2024, 11, 25),
                                                                      LocalTime.of(19, 20, 47))));

            Mockito.verify(eventRepository).save(newEvent);
        }

        @Test
        void handleScrapedEvent_Saved_Unchanged() {
            Mockito.when(eventRepository.getEventByOriginalId(newEvent.getOriginalId()))
                   .thenReturn(Optional.of(savedEvent));

            job.handleScrapedEvent(newEvent);

            // Check that nothing was saved because event values didn't change
            Mockito.verify(eventRepository, Mockito.times(0)).save(Mockito.any(Event.class));
        }

        @Test
        void handleScrapedEvent_Saved_Changed() {
            Mockito.when(eventRepository.getEventByOriginalId(newEvent.getOriginalId()))
                   .thenReturn(Optional.of(savedEvent));

            newEvent.setTitle("New Title");
            newEvent.getStartDateTime().addDays(1);

            job.handleScrapedEvent(newEvent);
            // Check that savedEvent values ahve been updated
            assertThat(savedEvent.getTitle(), is("New Title"));
            assertThat(savedEvent.getStartDateTime(), is(new DateTime(LocalDate.of(2024, 11, 26),
                                                                      LocalTime.of(19, 20, 47))));
            Mockito.verify(eventRepository).save(savedEvent);
        }
    }

    @Nested
    @DisplayName("hasEventChanged")
    class HasEventChangedTests {

        @Test
        void hasEventChanged_NotChanged() {
            var result = job.hasEventChanged(savedEvent, newEvent);
            assertThat(result, is(false));
        }

        @Test
        void hasEventChanged_NotChanged_OriginalId() {
            // The method does NOT check original id, so test that changing only that value still results in false
            newEvent.setOriginalId("New id");
            var result = job.hasEventChanged(savedEvent, newEvent);
            assertThat(result, is(false));
        }

        // Test each property change individually, since a change in any property should trigger an update

        @Test
        void hasEventChanged_Changed_Title() {
            newEvent.setTitle("New Event Title");
            var result = job.hasEventChanged(savedEvent, newEvent);
            assertThat(result, is(true));
        }

        @Test
        void hasEventChanged_Changed_Summary() {
            newEvent.setSummary("New Summary");
            var result = job.hasEventChanged(savedEvent, newEvent);
            assertThat(result, is(true));
        }

        // Tests below in this nested HasEventChangedTests class were generated using AI based on the
        // manually-written example tests above

        @Test
        void hasEventChanged_Changed_Description() {
            newEvent.setDescription("New Description");
            var result = job.hasEventChanged(savedEvent, newEvent);
            assertThat(result, is(true));
        }

        @Test
        void hasEventChanged_Changed_StartDateTime() {
            newEvent.getStartDateTime().addHours(1);
            var result = job.hasEventChanged(savedEvent, newEvent);
            assertThat(result, is(true));
        }

        @Test
        void hasEventChanged_Changed_EndDateTime() {
            newEvent.getEndDateTime().addHours(1);
            var result = job.hasEventChanged(savedEvent, newEvent);
            assertThat(result, is(true));
        }

        @Test
        void hasEventChanged_Changed_Host() {
            newEvent.setHost("New Host");
            var result = job.hasEventChanged(savedEvent, newEvent);
            assertThat(result, is(true));
        }

        @Test
        void hasEventChanged_Changed_OriginalLink() {
            newEvent.setOriginalLink("https://new.link");
            var result = job.hasEventChanged(savedEvent, newEvent);
            assertThat(result, is(true));
        }

        @Test
        void hasEventChanged_Changed_ImageUrl() {
            newEvent.setImageUrl("https://new.image.url");
            var result = job.hasEventChanged(savedEvent, newEvent);
            assertThat(result, is(true));
        }

        @Test
        void hasEventChanged_Changed_Tags() {
            newEvent.setTags(Set.of("NewTag"));
            var result = job.hasEventChanged(savedEvent, newEvent);
            assertThat(result, is(true));
        }

        @Test
        void hasEventChanged_Changed_Location_Type() {
            newEvent.getLocation().setType("New Type");
            var result = job.hasEventChanged(savedEvent, newEvent);
            assertThat(result, is(true));
        }

        @Test
        void hasEventChanged_Changed_Location_Name() {
            newEvent.getLocation().setName("New Location Name");
            var result = job.hasEventChanged(savedEvent, newEvent);
            assertThat(result, is(true));
        }

        @Test
        void hasEventChanged_Changed_Location_Address() {
            newEvent.getLocation().setAddress("New Address");
            var result = job.hasEventChanged(savedEvent, newEvent);
            assertThat(result, is(true));
        }

        @Test
        void hasEventChanged_Changed_Location_Url() {
            newEvent.getLocation().setLocationUrl("https://new.location.url");
            var result = job.hasEventChanged(savedEvent, newEvent);
            assertThat(result, is(true));
        }

        @Test
        void hasEventChanged_Changed_Location_GeoType() {
            newEvent.getLocation().getGeo().setType("New Geo Type");
            var result = job.hasEventChanged(savedEvent, newEvent);
            assertThat(result, is(true));
        }

        @Test
        void hasEventChanged_Changed_Location_GeoLatitude() {
            newEvent.getLocation().getGeo().setLatitude(123.45);
            var result = job.hasEventChanged(savedEvent, newEvent);
            assertThat(result, is(true));
        }

        @Test
        void hasEventChanged_Changed_Location_GeoLongitude() {
            newEvent.getLocation().getGeo().setLongitude(67.89);
            var result = job.hasEventChanged(savedEvent, newEvent);
            assertThat(result, is(true));
        }
    }

    @Test
    void updateSavedEventWithNewValues() {
        // Change all properties, including originalId
        newEvent.setOriginalId("nonsense");
        newEvent.setTitle("New Event Title");
        newEvent.setSummary("New Summary");
        newEvent.setDescription("New Description");
        newEvent.getStartDateTime().addHours(1);
        newEvent.getEndDateTime().addDays(1);
        newEvent.setHost("New Host");
        newEvent.setOriginalLink("https://new.link");
        newEvent.setImageUrl("https://new.image.url");
        newEvent.setTags(Set.of("NewTag"));
        newEvent.getLocation().setType("New Type");
        newEvent.getLocation().setName("New Location Name");
        newEvent.getLocation().setAddress("New Address");
        newEvent.getLocation().setLocationUrl("https://new.location.url");
        newEvent.getLocation().getGeo().setType("New Geo Type");
        newEvent.getLocation().getGeo().setLatitude(123.45);
        newEvent.getLocation().getGeo().setLongitude(67.89);

        job.updateSavedEventWithNewValues(savedEvent, newEvent);

        // Check that all properties are copied over EXCEPT originalId
        assertThat(savedEvent.getOriginalId(), is("505050"));
        assertThat(savedEvent.getTitle(), is("New Event Title"));
        assertThat(newEvent.getSummary(), is("New Summary"));
        assertThat(savedEvent.getDescription(), is("New Description"));
        assertThat(savedEvent.getStartDateTime(),
                   is(new DateTime(LocalDate.of(2024, 11, 25),
                                   LocalTime.of(20, 20, 47))));
        assertThat(savedEvent.getEndDateTime(),
                   is(new DateTime(LocalDate.of(2024, 11, 26),
                                   LocalTime.of(22, 5, 30))));
        assertThat(savedEvent.getHost(), is("New Host"));
        assertThat(savedEvent.getOriginalLink(), is("https://new.link"));
        assertThat(savedEvent.getImageUrl(), is("https://new.image.url"));
        assertThat(savedEvent.getTags(), is(Set.of("NewTag")));

        assertThat(savedEvent.getLocation(), is(sameInstance(newEvent.getLocation())));
        assertThat(savedEvent.getLocation().getGeo(), is(sameInstance(newEvent.getLocation().getGeo())));

        assertThat(savedEvent.getLocation().getType(), is("New Type"));
        assertThat(savedEvent.getLocation().getName(), is("New Location Name"));
        assertThat(savedEvent.getLocation().getAddress(), is("New Address"));
        assertThat(savedEvent.getLocation().getLocationUrl(), is("https://new.location.url"));
        assertThat(savedEvent.getLocation().getGeo().getType(), is("New Geo Type"));
        assertThat(savedEvent.getLocation().getGeo().getLatitude(), is(123.45));
        assertThat(savedEvent.getLocation().getGeo().getLongitude(), is(67.89));
    }
}
