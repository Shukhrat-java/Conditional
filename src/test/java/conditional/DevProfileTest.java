package conditional;

import conditional.service.SystemProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = "netology.profile.dev=true")
class DevProfileTest {

    @Autowired
    private SystemProfile systemProfile;

    @Test
    void testDevProfileActive() {
        assertNotNull(systemProfile, "DevProfile should be active");
        assertEquals("Current profile is dev", systemProfile.getProfile());
    }
}