package fyi.shycat.site.rest;


import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = HealthController.class)
public class HealthControllerUnitTest {

    @Inject
    private MockMvc mvc;

    @Test
    void testHealth() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/health")).andExpect(status().isOk());
    }
}
