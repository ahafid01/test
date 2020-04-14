package fr.olaqin.pfd.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import lombok.RequiredArgsConstructor;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@RequiredArgsConstructor
@Configuration
@EnableDynamoDBRepositories(basePackages = "fr.olaqin.pfd.repository")
public class DatabaseConfiguration {

    private final AmazonProperties amazonProperties;

    public AWSCredentialsProvider amazonAWSCredentialsProvider() {
        return new AWSStaticCredentialsProvider(amazonAWSCredentials());
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(amazonProperties.getAws().getAccesskey(), amazonProperties.getAws().getSecretKey());
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        if (amazonProperties.getDynamodb() == null) {
            return AmazonDynamoDBClientBuilder.standard()
                    .withCredentials(amazonAWSCredentialsProvider())
                    .withRegion(amazonProperties.getAws().getRegion())
                    .build();
        }
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonProperties.getDynamodb().getEndpoint(), amazonProperties.getAws().getRegion()))
                .withCredentials(amazonAWSCredentialsProvider())
                .build();
    }

}
