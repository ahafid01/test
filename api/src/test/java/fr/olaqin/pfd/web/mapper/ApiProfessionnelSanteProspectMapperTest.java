package fr.olaqin.pfd.web.mapper;

import fr.olaqin.pfd.api.model.ApiProfessionnelSanteProspect;
import fr.olaqin.pfd.entity.ProfessionnelSanteProspect;
import fr.olaqin.pfd.enums.RaisonQualif;
import fr.olaqin.pfd.enums.ResultatQualif;
import fr.olaqin.pfd.enums.TypeProfession;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static fr.olaqin.pfd.enums.Civilite.MONSIEUR;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ApiProfessionnelSanteProspectMapperTest {

    private static final ApiProfessionnelSanteProspectMapper INSTANCE = Mappers.getMapper(ApiProfessionnelSanteProspectMapper.class);

    @Test
    void toApiNullTest() {
        assertNull(INSTANCE.toModel((ApiProfessionnelSanteProspect) null));

    }

    @Test
    void toApiTest() {
        final ProfessionnelSanteProspect prospect = ProfessionnelSanteProspect.builder()
                .id("ID")
                .civilite(MONSIEUR)
                .nom("MARTIN")
                .prenom("Patrice")
                .email("p.martin@olaqin.fr")
                .codePostal("75013")
                .accepteEtreRecontacte(true)
                .resultatQualif(ResultatQualif.OK)
                .profession(TypeProfession.AUTRE)
                .raisonQualif(RaisonQualif.AUCUNE_VILLE_RETOURNEE)
                .telephone("0607172154")
                .reponseAutreProfession("VRAI")
                .build();

        Object result = INSTANCE.toApi(prospect);
        assertNotNull(result);
        MatcherAssert.assertThat(result, instanceOf(ApiProfessionnelSanteProspect.class));

        final ApiProfessionnelSanteProspect apiProfessionnelSanteProspect = (ApiProfessionnelSanteProspect) result;

        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(apiProfessionnelSanteProspect.getId()).isEqualTo(prospect.getId());
        softly.assertThat(apiProfessionnelSanteProspect.getCivilite().getValue()).isEqualTo(prospect.getCivilite().name());
        softly.assertThat(apiProfessionnelSanteProspect.getNom()).isEqualTo(prospect.getNom());
        softly.assertThat(apiProfessionnelSanteProspect.getPrenom()).isEqualTo(prospect.getPrenom());

        softly.assertThat(apiProfessionnelSanteProspect.getEmail()).isEqualTo(prospect.getEmail());
        softly.assertThat(apiProfessionnelSanteProspect.getCodePostal()).isEqualTo(prospect.getCodePostal());
        softly.assertThat(apiProfessionnelSanteProspect.isAccepteEtreRecontacte()).isEqualTo(prospect.getAccepteEtreRecontacte());
        softly.assertThat(apiProfessionnelSanteProspect.getResultatQualif().getValue()).isEqualTo(prospect.getResultatQualif().name());
        softly.assertThat(apiProfessionnelSanteProspect.getProfession().getValue()).isEqualTo(prospect.getProfession().name());
        softly.assertThat(apiProfessionnelSanteProspect.getRaisonQualif().getValue()).isEqualTo(prospect.getRaisonQualif().name());
        softly.assertThat(apiProfessionnelSanteProspect.getTelephone()).isEqualTo(prospect.getTelephone());
        softly.assertThat(apiProfessionnelSanteProspect.getReponseAutreProfession()).isEqualTo(prospect.getReponseAutreProfession());
        softly.assertAll();
    }
}