package conditional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DockerIntegrationTest {

    private static GenericContainer<?> devApp;
    private static GenericContainer<?> prodApp;

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @BeforeAll
    public static void setUp() {
        // Создаем и стартуем контейнеры как требует задание
        devApp = new GenericContainer<>("devapp:latest")
                .withExposedPorts(8080);
        prodApp = new GenericContainer<>("prodapp:latest")
                .withExposedPorts(8081);

        devApp.start();
        prodApp.start();
    }

    @Test
    void testDevAppReturnsCorrectResponse() {
        Integer devMappedPort = devApp.getMappedPort(8080);
        String devUrl = "http://localhost:" + devMappedPort + "/profile";

        ResponseEntity<String> devResponse = restTemplate.getForEntity(devUrl, String.class);

        assertEquals(200, devResponse.getStatusCodeValue());
        assertEquals("Current profile is dev", devResponse.getBody());
    }

    @Test
    void testProdAppReturnsCorrectResponse() {
        Integer prodMappedPort = prodApp.getMappedPort(8081);
        String prodUrl = "http://localhost:" + prodMappedPort + "/profile";

        ResponseEntity<String> prodResponse = restTemplate.getForEntity(prodUrl, String.class);

        assertEquals(200, prodResponse.getStatusCodeValue());
        assertEquals("Current profile is production", prodResponse.getBody());
    }
}