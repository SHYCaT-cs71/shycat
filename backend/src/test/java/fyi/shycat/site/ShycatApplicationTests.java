package fyi.shycat.site;

import fyi.shycat.site.rest.EventController;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
class ShycatApplicationTests {

    @Inject
    private EventController eventController;

    @Test
    void contextLoads() {
        assertThat(eventController, is(notNullValue()));
    }

}
