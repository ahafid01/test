package fr.olaqin.pfd;

import fr.olaqin.pfd.config.AmazonProperties;
import fr.olaqin.pfd.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({AmazonProperties.class, ApplicationProperties.class})
public class PfdApplication {

	public static void main(String[] args) {
		SpringApplication.run(PfdApplication.class, args);
	}

}
