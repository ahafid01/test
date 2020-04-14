package fr.olaqin.pfd;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
@ActiveProfiles("test")
class PfdApplicationTests {

	//@Test
	void contextLoads() {
	}

}
