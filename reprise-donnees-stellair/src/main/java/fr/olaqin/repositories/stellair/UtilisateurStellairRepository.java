package fr.olaqin.repositories.stellair;


import fr.olaqin.entities.stellair.AbstractUserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurStellairRepository extends PagingAndSortingRepository<AbstractUserEntity, Long> {
}
