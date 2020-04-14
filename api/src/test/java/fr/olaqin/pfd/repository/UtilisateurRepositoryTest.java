package fr.olaqin.pfd.repository;

import fr.olaqin.pfd.entity.Adresse;
import fr.olaqin.pfd.entity.Utilisateur;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilisateurRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    @DisplayName("retourne un utilisateur par email")
    public void findByEmail() {
        final Adresse adresseLivraison = Adresse.builder()
                .nomComplet("Mr David BRAS")
                .ligne1("Ingenico Group S.A.")
                .ligne2("28/32 boulevard de Grenelle")
                .codePostal("75015")
                .ville("PARIS")
                .build();

        final Utilisateur utilisateur = Utilisateur.builder()
                .id("a91a0096-55fe-4a39-a46c-5f93ea066051")
                .nom("BRAS")
                .prenom("David")
                .dateNaissance(LocalDate.of(1981, 05, 07))
                .email("d.bras@olaqin.fr")
                .telephonePrincipal("0601132115")
                .telephoneSecondaire("0145002041")
                .adresseLivraison(adresseLivraison)
                .build();

        utilisateurRepository.save(utilisateur);

        final Optional<Utilisateur> result = utilisateurRepository.findByEmail("d.bras@olaqin.fr");

        assertTrue(result.isPresent());
        assertThat(result.get(), instanceOf(Utilisateur.class));
    }

    @Test
    @DisplayName("retourne un utilisateur par email")
    public void findByEmail_NotFound() {
        final Optional<Utilisateur> utilisateur = utilisateurRepository.findByEmail("mail.inexistant@olaqin.fr");
        assertFalse(utilisateur.isPresent());
    }
}