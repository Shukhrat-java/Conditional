package conditional;

import conditional.service.SystemProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = "netology.profile.dev=false")
class ProdProfileTest {

    @Autowired
    private SystemProfile systemProfile;

    @Test
    void testProdProfileActive() {
        assertNotNull(systemProfile, "ProductionProfile should be active");
        assertEquals("Current profile is production", systemProfile.getProfile());
    }
}