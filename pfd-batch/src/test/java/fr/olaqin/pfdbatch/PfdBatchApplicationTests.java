package fr.olaqin.pfdbatch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class PfdBatchApplicationTests {

	@Autowired
	private AnnuaireSchedule tasks;

	@Test
	void contextLoads() {
		assertThat(tasks).isNotNull();
	}

}
