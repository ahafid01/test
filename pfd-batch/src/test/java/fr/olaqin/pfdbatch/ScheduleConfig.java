package fr.olaqin.pfdbatch;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.ActiveProfiles;

@Configuration
@EnableScheduling
@ComponentScan("fr.olaqin.pfdbatch")
@ActiveProfiles("test")
public class ScheduleConfig {
}
