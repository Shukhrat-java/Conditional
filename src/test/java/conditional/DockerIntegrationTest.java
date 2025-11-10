package conditional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class DockerIntegrationTest {

    // Dev
    @Container
    private static final GenericContainer<?> devApp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);

    // Prod
    @Container
    private static final GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void testDevContainerReturnsDevProfile() {
        // маппинг порта для dev
        Integer devMappedPort = devApp.getMappedPort(8080);
        String devUrl = "http://localhost:" + devMappedPort + "/profile";

        ResponseEntity<String> devResponse = restTemplate.getForEntity(devUrl, String.class);

        assertEquals(200, devResponse.getStatusCodeValue());
        assertEquals("Current profile is dev", devResponse.getBody());
    }

    @Test
    void testProdContainerReturnsProdProfile() {
        // маппинг порта для prod
        Integer prodMappedPort = prodApp.getMappedPort(8081);
        String prodUrl = "http://localhost:" + prodMappedPort + "/profile";

        ResponseEntity<String> prodResponse = restTemplate.getForEntity(prodUrl, String.class);

        assertEquals(200, prodResponse.getStatusCodeValue());
        assertEquals("Current profile is production", prodResponse.getBody());
    }
}