package fr.olaqin.pfd.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import fr.olaqin.pfd.config.DynamodbConfig;
import fr.olaqin.pfd.entity.AnnuaireProfessionnelSanteEnActivite;
import fr.olaqin.pfd.entity.CommonEntity;
import fr.olaqin.pfd.entity.ProfessionnelSanteProspect;
import fr.olaqin.pfd.entity.Utilisateur;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {PropertyPlaceholderAutoConfiguration.class, DynamodbConfig.class}
)
@ActiveProfiles("test")
public abstract class AbstractRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(AbstractRepositoryTest.class);
    static DynamoDBProxyServer server;
    @Autowired
    private AmazonDynamoDB amazonDynamoDB;
    private DynamoDBMapper mapper;

    @BeforeAll
    public static void beforAll() throws Exception {
        final String[] localArgs = {"-inMemory", "-port", "8011"};
        server = ServerRunner.createServerFromCommandLineArgs(localArgs);
        server.start();
    }

    @AfterAll
    public static void afterAll() throws Exception {
        server.stop();
    }

    @BeforeEach
    public void init() throws Exception {
        createTables(ProfessionnelSanteProspect.class);
        createAnnuaireTable();
        createTables(Utilisateur.class);
    }

    private <T extends CommonEntity> void createTables(Class<T> t) throws InterruptedException {
        mapper = new DynamoDBMapper(amazonDynamoDB);
        ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput(1L, 1L);
        CreateTableRequest ctr = mapper.generateCreateTableRequest(t)
                .withProvisionedThroughput(provisionedThroughput);

        TableUtils.createTableIfNotExists(amazonDynamoDB, ctr);
        TableUtils.waitUntilActive(amazonDynamoDB, ctr.getTableName());
        log.info("Table {} is active", ctr.getTableName());
    }

    @AfterEach
    public void destroy() {
        deleteTables(ProfessionnelSanteProspect.class);
        deleteTables(AnnuaireProfessionnelSanteEnActivite.class);
        deleteTables(Utilisateur.class);
    }

    public <T extends CommonEntity> void deleteTables(Class<T> t) {
        DeleteTableRequest dtr = mapper.generateDeleteTableRequest(t);
        TableUtils.deleteTableIfExists(amazonDynamoDB, dtr);
        log.info("Deleted table {}", dtr.getTableName());
    }

    private void createAnnuaireTable() throws InterruptedException {
        mapper = new DynamoDBMapper(amazonDynamoDB);
        ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput(1L, 1L);

        GlobalSecondaryIndex gsi = new GlobalSecondaryIndex()
                .withIndexName(AnnuaireProfessionnelSanteEnActivite.NOM_PRENOM_CODE_POSTAL_INDEX)
                .withKeySchema(new KeySchemaElement().withAttributeName("NomExerciceSearch").withKeyType(KeyType.HASH),
                        new KeySchemaElement().withAttributeName("PrenomExerciceSearch").withKeyType(KeyType.RANGE))
                .withProvisionedThroughput(provisionedThroughput)
                .withProjection(new Projection()
                        .withProjectionType(ProjectionType.INCLUDE)
                        .withNonKeyAttributes("CodePostalCoordStructure"));

        CreateTableRequest ctr = mapper.generateCreateTableRequest(AnnuaireProfessionnelSanteEnActivite.class)
                .withProvisionedThroughput(provisionedThroughput);
        ctr.setGlobalSecondaryIndexes(Arrays.asList(gsi));

        TableUtils.createTableIfNotExists(amazonDynamoDB, ctr);
        TableUtils.waitUntilActive(amazonDynamoDB, ctr.getTableName());
        log.info("Table {} is active", ctr.getTableName());
    }

    protected AnnuaireProfessionnelSanteEnActivite getAnnuairePS(String nom, String prenom, String codePostal, String civilite, String codeDepartement) {
        return AnnuaireProfessionnelSanteEnActivite.builder()
                .nomExercice(nom)
                .prenomExercice(prenom)
                .codePostalCoordStructure(codePostal)
                .libelleCivilite(civilite)
                .codeDepartementStructure(codeDepartement)
                .build();
    }

    protected ProfessionnelSanteProspect getProfessionnelSante(String nom, String prenom, String codePostal) {
        return ProfessionnelSanteProspect.builder()
                .nom(nom)
                .prenom(prenom)
                .codePostal(codePostal)
                .build();
    }

}
