package fr.olaqin.pfd.repository;


import fr.olaqin.pfd.entity.ProfessionnelSanteProspect;
import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


class ProfessionnelSanteProspectRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private ProfessionnelSanteProspectRepository repository;

    @Test
    @DisplayName("Implementation de l'API Spring data dynamodb fonctionne avec plusieurs enregistrements")
    public void testSpringDataDynamodb() {
        ProfessionnelSanteProspect ps1 = repository.save(getProfessionnelSante("haf1", "abder1", "12345"));
        ProfessionnelSanteProspect ps2 = repository.save(getProfessionnelSante("haf2", "abder2", "54321"));

        Iterable<ProfessionnelSanteProspect> all = repository.findAll();
        List<ProfessionnelSanteProspect> professionnelSanteProspects = IterableUtils.toList(all);
        assertThat(professionnelSanteProspects)
                .isNotEmpty()
                .hasSize(2)
                .extracting(ProfessionnelSanteProspect::getId, ProfessionnelSanteProspect::getNom, ProfessionnelSanteProspect::getPrenom, ProfessionnelSanteProspect::getCodePostal)
                .contains(
                        tuple(ps1.getId(), ps1.getNom(), ps1.getPrenom(), ps1.getCodePostal()),
                        tuple(ps2.getId(), ps2.getNom(), ps2.getPrenom(), ps2.getCodePostal())
                );
    }

}