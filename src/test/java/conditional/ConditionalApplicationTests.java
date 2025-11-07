package conditional;

import conditional.service.SystemProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConditionalApplicationTests {

    @Autowired(required = false)
    private SystemProfile systemProfile;

    @Test
    void contextLoads() {
        // Проверяем, что контекст Spring загружается
        assertNotNull(systemProfile, "SystemProfile bean should be loaded");
    }
}