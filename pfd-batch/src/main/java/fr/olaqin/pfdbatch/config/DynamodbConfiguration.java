package fr.olaqin.pfdbatch.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Configuration
public class DynamodbConfiguration {
    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;
    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;
    @Value("${amazon.dynamodb.endpoint}")
    private String amazonAWSEndpoint;
    @Value("${amazon.aws.region}")
    private String amazonAWSRegion;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        if(isEmpty(amazonAWSEndpoint)) {
            return AmazonDynamoDBClientBuilder.standard()
                    .withCredentials(getCredentialsProvider())
                    .withRegion(amazonAWSRegion)
                    .build();
        }
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonAWSEndpoint,amazonAWSRegion))
                .withCredentials(getCredentialsProvider())
                .build();
    }

    private AWSStaticCredentialsProvider getCredentialsProvider() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey));
    }


}
