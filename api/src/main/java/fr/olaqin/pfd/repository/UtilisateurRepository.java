package fr.olaqin.pfd.repository;

import fr.olaqin.pfd.entity.Utilisateur;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository utilisateur
 */
@EnableScan
public interface UtilisateurRepository extends CrudRepository<Utilisateur, String> {
    /**
     * Permet de récupérer un utilisateur
     *
     * @param email adresse mail
     * @return utilisateur
     */
    Optional<Utilisateur> findByEmail(String email);
}
