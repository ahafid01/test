package fr.olaqin.pfd.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "amazon", ignoreUnknownFields = false)
public class AmazonProperties {
    private Aws aws;
    private Dynamodb dynamodb;
    private SimpleEmail ses;

    @Data
    public static class Aws {
        private String accesskey;
        private String secretKey;
        private String region;
    }

    @Data
    public static class Dynamodb {
        private String endpoint;
    }

    @Data
    public static class SimpleEmail {
        private String from;
        private String region;
        private String endpoint;
    }
}
