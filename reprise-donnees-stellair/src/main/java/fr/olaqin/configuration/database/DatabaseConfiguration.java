package fr.olaqin.configuration.database;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * dynamodb configuration
 *
 * @author galaxie
 * @version 1.0
 */
@RequiredArgsConstructor
@Configuration
public class DatabaseConfiguration {

    private final AmazonProperties amazonProperties;

    public AWSCredentialsProvider amazonAWSCredentialsProvider() {
        return new AWSStaticCredentialsProvider(amazonAWSCredentials());
    }

    /**
     * AWS credentials
     *
     * @return AWSCredentials
     */
    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(amazonProperties.getAws().getAccesskey(), amazonProperties.getAws().getSecretKey());
    }

    /**
     * Interface for accessing DynamoDB
     *
     * @return AmazonDynamoDB
     */
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
