package fr.olaqin.configuration.batch;

import fr.olaqin.entities.galaxie.Utilisateur;
import fr.olaqin.entities.stellair.AbstractUserEntity;
import fr.olaqin.repositories.stellair.UtilisateurStellairRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import static java.util.Collections.singletonMap;

/**
 * global batch configuration
 *
 * @author galaxie
 * @version 1.0
 */
@RequiredArgsConstructor
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final JobBuilderFactory jobs;
    private final StepBuilderFactory steps;
    private final ItemWriter<Utilisateur> dynamoDbWriter;
    private final ItemProcessor<AbstractUserEntity, Utilisateur> h2ToDynamoDbProcessor;
    private final UtilisateurStellairRepository utilisateurStellairRepository;

    /**
     * step
     *
     * @return step
     */
    @Bean
    public Step repriseDonneesStellairStep() {
        return steps.get("repriseDonneesStellairStep")
                .<AbstractUserEntity, Utilisateur>chunk(100)
                .reader(h2Reader())
                .processor(h2ToDynamoDbProcessor)
                .writer(dynamoDbWriter)
                .build();
    }

    /**
     * job
     *
     * @return job
     */
    @Bean
    public Job repriseDonneesStellairJob() {
        return jobs.get("repriseDonneesStellairJob")
                .incrementer(new RunIdIncrementer())
                .start(repriseDonneesStellairStep())
                .build();
    }

    /**
     * reader
     *
     * @return reader
     */
    @Bean
    public ItemReader<AbstractUserEntity> h2Reader() {
        RepositoryItemReader<AbstractUserEntity> reader = new RepositoryItemReader<>();
        reader.setRepository(utilisateurStellairRepository);
        reader.setMethodName("findAll");
        reader.setPageSize(100);
        reader.setSort(singletonMap("id", Sort.Direction.ASC));
        return reader;
    }

}
