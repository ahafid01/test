package fr.olaqin.pfdbatch;

import org.awaitility.Durations;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
class AnnuaireScheduleTest {
    @SpyBean
    AnnuaireSchedule tasks;

    @Test
    public void invokeAnnuaireSchedule() {
        await().atMost(Durations.FIVE_SECONDS).untilAsserted(() -> {
            verify(tasks, atLeast(2)).importDataFromAsipToGalaxieDynamodb();
        });
    }

}