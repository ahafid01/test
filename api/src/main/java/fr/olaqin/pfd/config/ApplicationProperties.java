package fr.olaqin.pfd.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.isEmpty;


@Data
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    private Stellair stellair;

    @Data
    public static class Stellair {
        private String urlForm;
        private String subject;
        private String to;
        private String templateName;
        public List<String> getToAddresses() {
            return isEmpty(to) ? null : asList(to.split(","));
        }
    }

}
