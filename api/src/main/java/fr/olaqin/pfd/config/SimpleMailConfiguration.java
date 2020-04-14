package fr.olaqin.pfd.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class SimpleMailConfiguration {

    private final AmazonProperties amazonProperties;

    public AWSCredentialsProvider amazonAWSCredentialsProvider() {
        return new AWSStaticCredentialsProvider(amazonAWSCredentials());
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(amazonProperties.getAws().getAccesskey(), amazonProperties.getAws().getSecretKey());
    }

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        if (amazonProperties.getSes().getEndpoint() == null) {
            return AmazonSimpleEmailServiceClientBuilder.standard()
                    .withCredentials(amazonAWSCredentialsProvider())
                    .withRegion(amazonProperties.getSes().getRegion()).build();
        }
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(amazonAWSCredentialsProvider())
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonProperties.getSes().getEndpoint(), amazonProperties.getSes().getRegion())).build();
    }

}

