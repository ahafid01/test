package fr.olaqin;

import fr.olaqin.configuration.database.AmazonProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({AmazonProperties.class})
public class RepriseDonneesStellairApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepriseDonneesStellairApplication.class, args);
    }

}
