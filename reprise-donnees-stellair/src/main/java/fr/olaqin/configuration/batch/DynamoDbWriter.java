package fr.olaqin.configuration.batch;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverterFactory;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import fr.olaqin.entities.galaxie.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * writer configuration
 *
 * @author galaxie
 * @version 1.0
 */
@RequiredArgsConstructor
@Component
public class DynamoDbWriter implements ItemWriter<Utilisateur> {

    private final AmazonDynamoDB amazonDynamoDB;

    @Override
    public void write(List<? extends Utilisateur> items) {
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);

        DynamoDBMapperConfig config = DynamoDBMapperConfig.builder()
                .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.CLOBBER)
                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.EVENTUAL)
                .withPaginationLoadingStrategy(DynamoDBMapperConfig.PaginationLoadingStrategy.LAZY_LOADING)
                .withTableNameResolver(DynamoDBMapperConfig.DefaultTableNameResolver.INSTANCE)
                .withBatchWriteRetryStrategy(DynamoDBMapperConfig.DefaultBatchWriteRetryStrategy.INSTANCE)
                .withBatchLoadRetryStrategy(DynamoDBMapperConfig.DefaultBatchLoadRetryStrategy.INSTANCE)
                .withTypeConverterFactory(DynamoDBTypeConverterFactory.standard())
                .build();

        List<Utilisateur> objectsToDelete = items.stream().map(item -> {

            Map<String, String> attributeNames = new HashMap<>();
            attributeNames.put("#Email", "Email");

            Map<String, AttributeValue> attributeValues = new HashMap<>();
            attributeValues.put(":Email", new AttributeValue().withS(item.getEmail()));

            DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression()
                    .withFilterExpression("#Email = :Email")
                    .withExpressionAttributeNames(attributeNames)
                    .withExpressionAttributeValues(attributeValues);

            return mapper.scan(Utilisateur.class, dynamoDBScanExpression);

        }).flatMap(List::stream)
                .collect(Collectors.toList());

        mapper.batchWrite(items, objectsToDelete, config);
    }

}
