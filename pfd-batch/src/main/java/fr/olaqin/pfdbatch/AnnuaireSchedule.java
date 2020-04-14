package fr.olaqin.pfdbatch;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.*;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import fr.olaqin.pfdbatch.entity.AnnuaireProfessionnelSanteEnActivite;
import fr.olaqin.pfdbatch.mapper.AnnuaireProfessionnelSanteEnActiviteMapper;
import fr.olaqin.pfdbatch.model.AnnuaireProfessionnelSanteEnActiviteDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.Proxy;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.amazonaws.services.dynamodbv2.model.KeyType.HASH;
import static com.amazonaws.services.dynamodbv2.model.KeyType.RANGE;
import static com.amazonaws.services.dynamodbv2.model.ScalarAttributeType.S;
import static fr.olaqin.pfdbatch.entity.AnnuaireProfessionnelSanteEnActivite.ENTITY_NAME;
import static fr.olaqin.pfdbatch.entity.AnnuaireProfessionnelSanteEnActivite.NOM_PRENOM_CODE_POSTAL_INDEX;
import static java.util.Collections.emptyList;

@Slf4j
@Component
public class AnnuaireSchedule {

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Value("${application.asip.url}")
    private String asipUrl;

    @Value("${application.asip.size}")
    private Integer size;

    @Scheduled(cron = "${application.asip.cron}" )
    public void importDataFromAsipToGalaxieDynamodb() throws IOException {
        log.info("Starting scheduler");

        disableSslValidation();

        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);
        InputStream is = new URL(asipUrl)
                .openConnection(Proxy.NO_PROXY)
                .getInputStream();
        ZipInputStream zip = new ZipInputStream(is);
        ZipEntry ze;
        while ((ze = zip.getNextEntry()) != null) {

            log.info("File: {} Size: {} Last Modified {} {}",
                    LocalDate.ofEpochDay(ze.getTime() / 86400000L));

            if (ze.getName().startsWith("PS_LibreAcces_Personne_activite")) {
                try (Reader reader = new BufferedReader(new InputStreamReader(zip, "UTF-8"))) {
                    CsvToBean<AnnuaireProfessionnelSanteEnActiviteDto> csvToBean = new CsvToBeanBuilder(reader)
                            .withType(AnnuaireProfessionnelSanteEnActiviteDto.class)
                            .withSeparator('|')
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();

                    List<AnnuaireProfessionnelSanteEnActiviteDto> users = csvToBean.parse();
                    if (CollectionUtils.isEmpty(users)) {
                        throw new IllegalArgumentException("Le fichier ne contient pas de données.");
                    }

                   // clearTable();
                    PaginatedScanList<AnnuaireProfessionnelSanteEnActivite> scan = mapper.scan(AnnuaireProfessionnelSanteEnActivite.class, new DynamoDBScanExpression());
                    mapper.batchWrite(emptyList(), scan, getMapperConfig());

                    log.info("Sauvegrade dans la table {}.", ENTITY_NAME);
                    List<AnnuaireProfessionnelSanteEnActivite> psListToSave = AnnuaireProfessionnelSanteEnActiviteMapper.INSTANCE.map(users);
                    if(size == null) {
                        IntStream.range(0, 10).parallel().forEach(i -> {
                            log.info("iteration {}.", i);
                            mapper.batchWrite(psListToSave.subList(i * (psListToSave.size()/10), (i + 1) * (psListToSave.size()/10)), emptyList(), getMapperConfig());
                            mapper.batchWrite(psListToSave.subList(i * (psListToSave.size()/10), (i + 1) * (psListToSave.size()/10)), emptyList(), getMapperConfig());
                        });
                    } else {
                        mapper.batchWrite(psListToSave.subList(0, size), emptyList(), getMapperConfig());
                    }
                    log.info("Fin de sauvegarde dans la table {}.", ENTITY_NAME);

                    break;
                }
            }
        }

    }

    private void disableSslValidation() {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
        }
    }

    // Clear Table

    private void clearTable() {
        try {
            log.info("Suppression de la table {}.", ENTITY_NAME);
            amazonDynamoDB.deleteTable(ENTITY_NAME);
        } catch (ResourceNotFoundException e) {
            log.info("La table {} ne peut pas être supprimée car elle n'existe pas.", ENTITY_NAME);
        }
        waitDeleteTbale();
        log.info("La table {} est supprimée.", ENTITY_NAME);
        log.info("Création de la table {}.", ENTITY_NAME);
        ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput(5l,  5l);
        GlobalSecondaryIndex globalSecondaryIndex = new GlobalSecondaryIndex().withIndexName(NOM_PRENOM_CODE_POSTAL_INDEX)
                .withKeySchema(new KeySchemaElement("NomExerciceSearch", HASH), new KeySchemaElement("PrenomExerciceSearch", RANGE))
                .withProvisionedThroughput(provisionedThroughput)
                .withProjection(new Projection().withProjectionType(ProjectionType.INCLUDE).withNonKeyAttributes("CodePostalCoordStructure"));

        CreateTableRequest table = new CreateTableRequest()
                .withTableName(ENTITY_NAME)
                .withKeySchema(new KeySchemaElement("Id", HASH))
                .withAttributeDefinitions(new AttributeDefinition("Id", S),
                        new AttributeDefinition("NomExerciceSearch", S),
                        new AttributeDefinition("PrenomExerciceSearch", S))
                .withProvisionedThroughput(provisionedThroughput)
                .withGlobalSecondaryIndexes(globalSecondaryIndex);

        amazonDynamoDB.createTable(table);
        waitCreateTable();
        log.info("La table {} est créee.", ENTITY_NAME);
    }

    private DynamoDBMapperConfig getMapperConfig() {
        return DynamoDBMapperConfig.builder()
                .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.CLOBBER)
                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.EVENTUAL)
                .withPaginationLoadingStrategy(DynamoDBMapperConfig.PaginationLoadingStrategy.LAZY_LOADING)
                .withTableNameResolver(DynamoDBMapperConfig.DefaultTableNameResolver.INSTANCE)
                .withBatchWriteRetryStrategy(DynamoDBMapperConfig.DefaultBatchWriteRetryStrategy.INSTANCE)
                .withBatchLoadRetryStrategy(DynamoDBMapperConfig.DefaultBatchLoadRetryStrategy.INSTANCE)
                .withTypeConverterFactory(DynamoDBTypeConverterFactory.standard())
                .build();
    }

    private void waitCreateTable() {
        int i = 0;
        do {
            try {
                amazonDynamoDB.describeTable(ENTITY_NAME);
                break;

            } catch (ResourceNotFoundException e) {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException ex) {
                }
            }
        } while(i++ < 30);
    }

    private void waitDeleteTbale() {
        int i = 0;
        do {
            try {
                amazonDynamoDB.describeTable(ENTITY_NAME);
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                }
            } catch (ResourceNotFoundException e) {
                break;
            }
        } while(i++ < 30);
    }

}
