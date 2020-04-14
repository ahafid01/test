package fr.olaqin.pfd.repository;


import fr.olaqin.pfd.entity.AnnuaireProfessionnelSanteEnActivite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


class AnnuaireProfessionnelSanteEnActiviteRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private AnnuaireProfessionnelSanteRepository repository;

    @Test
    @DisplayName("retourne un objet par nom et prenom")
    public void testFindAllByNomExerciceSearchAndPrenomExerciceSearch() {

        AnnuaireProfessionnelSanteEnActivite ps1 = getAnnuairePS("nom1", "prenom1", "01111", "Monsieur", "01");
        AnnuaireProfessionnelSanteEnActivite ps2 = getAnnuairePS("nom2", "prenom2", "02222", "Madame", "02");
        AnnuaireProfessionnelSanteEnActivite ps3 = getAnnuairePS("nom3", "prenom3", "03333", "Monsieur", "03");
        repository.saveAll(Arrays.asList(ps1, ps2, ps3));
        List<AnnuaireProfessionnelSanteEnActivite> savedList = repository.findAllByNomExerciceSearchAndPrenomExerciceSearch(ps2.getNomExercice(), ps2.getPrenomExercice());

        assertThat(savedList)
                .isNotNull()
                .hasSize(1)
                .extracting(AnnuaireProfessionnelSanteEnActivite::getNomExerciceSearch, AnnuaireProfessionnelSanteEnActivite::getPrenomExerciceSearch, AnnuaireProfessionnelSanteEnActivite::getCodePostalCoordStructure)
                .contains(
                        tuple(ps2.getNomExercice(), ps2.getPrenomExercice(), ps2.getCodePostalCoordStructure())
                );
    }

}