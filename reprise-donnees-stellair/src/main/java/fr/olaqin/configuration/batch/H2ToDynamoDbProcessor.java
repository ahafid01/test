package fr.olaqin.configuration.batch;

import fr.olaqin.entities.galaxie.Utilisateur;
import fr.olaqin.entities.galaxie.UtilisateurStrategy;
import fr.olaqin.entities.stellair.AbstractUserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * processor configuration
 *
 * @author galaxie
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class H2ToDynamoDbProcessor implements ItemProcessor<AbstractUserEntity, Utilisateur> {

    @Override
    public Utilisateur process(AbstractUserEntity item) {
        final String type = item.getType();
        final UtilisateurStrategy strategy = UtilisateurStrategy.valueOf(UtilisateurStrategy.class, type);
        if (Objects.isNull(strategy)) {
            // Il ne faut pas bloqué le traitement, la journalisation suffise
            log.error(String.format("Le type %s est invalide pour l'utilisateur id = %s", item.getType(), item.getId()));
        }

        return UtilisateurStrategy.valueOf(UtilisateurStrategy.class, type)
                .creer(item);
    }

}
