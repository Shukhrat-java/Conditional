package conditional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "netology.profile.dev=true")
class ProfileControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testProfileEndpoint() {
        String url = "http://localhost:" + port + "/profile";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Current profile is dev"));
    }
}